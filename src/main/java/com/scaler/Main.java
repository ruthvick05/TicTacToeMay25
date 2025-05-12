package com.scaler;

import com.scaler.controller.GameController;
import com.scaler.exceptions.InvalidMoveException;
import com.scaler.models.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        GameController gameController = new GameController();

        int dimension = 3;
        List<Player> players = new ArrayList<>();

        players.add(new Player("Abhishek", new Symbol('X'))); // HUMAN
        players.add(new Bot("Scaler", new Symbol('O'), BotDifficultyLevel.EASY));

        Game game = gameController.startGame(dimension, players);

        while (gameController.getGameState(game).equals(GameState.IN_PROGRESS)) {
            gameController.printBoard(game);

            gameController.makeMove(game);
        }

        //GameState either will be ENDED or DRAW.
        if (gameController.getGameState(game).equals(GameState.ENDED)) {
            gameController.printBoard(game);
            System.out.println(gameController.getWinner(game).getName() + " has WON the game.");
        } else {
            System.out.println("GAME DRAW!");
        }
    }
}