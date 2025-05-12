package com.scaler.models;

import com.scaler.exceptions.InvalidMoveException;
import com.scaler.strategies.winningstrategy.ColWinningStrategy;
import com.scaler.strategies.winningstrategy.DiagonalWinningStrategy;
import com.scaler.strategies.winningstrategy.RowWinningStrategy;
import com.scaler.strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerIndex);

        System.out.println("This is " + currentPlayer.getName() + "'s move");

        Move move = currentPlayer.makeMove();

        //check if move is valid or not.
        if (!validateMove(move)) {
            throw new InvalidMoveException("Invalid move, Please try again.");
        }

        //Make the move on the board.
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        System.out.println(currentPlayer.getName() + " is making a move at " + row + ", " + col);

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        nextPlayerIndex =  (nextPlayerIndex + 1) % players.size();

        Move finalMove = new Move(currentPlayer, cell);
        moves.add(finalMove);

        //Check if the current player has WON the game or not, if yes then end the game.
        if (checkWinner(finalMove, board)) {
            winner = currentPlayer;
            gameState = GameState.ENDED;
        } else if (moves.size() == board.getBoard().size() * board.getBoard().size()) {
            //DRAW -> if there's no place to make the move on the board.
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move, Board board) {
        //Check if the player has same symbol across any row, or any col or any diagonal.
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinner(move, board)) {
                return true;
            }
        }

        return false;
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (row < 0 ||
                row >= board.getDimension() ||
                col < 0 ||
                col >= board.getDimension()) {
            //outside the board.
            return false;
        }

        return board.getBoard().get(row).get(col).isEmpty();
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        //TODO
        public void validatePlayersCount() {

        }

        //TODO
        public void validateSymbols() {

        }

        //TODO
        public void validateBotCount() {

        }

        public void validateGame() {
            //Number of players should be dimension-1;
            //Players shouldn't same symbol
            //Only 1 bot is allowed per game.
            validatePlayersCount();
            validateSymbols();
            validateBotCount();
        }

        public Game build() {
            //Validate.
            validateGame();

            List<WinningStrategy> winningStrategies = new ArrayList<>();
            winningStrategies.add(new RowWinningStrategy());
            winningStrategies.add(new ColWinningStrategy());
            winningStrategies.add(new DiagonalWinningStrategy());

            return new Game(
                    dimension,
                    players,
                    winningStrategies
            );
        }
    }
}
