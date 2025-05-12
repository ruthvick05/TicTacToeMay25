package com.scaler.strategies.botplayingstrategy;

import com.scaler.models.Board;
import com.scaler.models.Cell;
import com.scaler.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        //Bot will iterate through the board and can play the symbol at the first available cell.
        for (List<Cell> cells : board.getBoard()) {
            for (Cell cell : cells) {
                if (cell.isEmpty()) {
                    return new Move(null, cell);
                }
            }
        }

        return null;
    }
}
