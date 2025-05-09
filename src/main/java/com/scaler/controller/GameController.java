package com.scaler.controller;

import com.scaler.models.Game;
import com.scaler.models.GameState;
import com.scaler.models.Player;

import java.util.List;

public class GameController {
    // Players will be interacting with GameController to make any operation.
    // startGame(), makeMove(), getGameState(), printBoard(), getWinner()

    public Game startGame(int dimension, List<Player> players) {
        return Game.getBuilder()
                   .setDimension(dimension)
                   .setPlayers(players)
                   .build();
    }

    public void makeMove(Game game) {

    }

    public GameState getGameState(Game game) {
        return game.getGameState();
    }

    public void printBoard(Game game) {

    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }
}
