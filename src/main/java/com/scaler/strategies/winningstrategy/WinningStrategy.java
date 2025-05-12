package com.scaler.strategies.winningstrategy;

import com.scaler.models.Board;
import com.scaler.models.Move;
import com.scaler.models.Player;

public interface WinningStrategy {
    boolean checkWinner(Move move, Board board);
}
