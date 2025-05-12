package com.scaler.strategies.winningstrategy;

import com.scaler.models.Board;
import com.scaler.models.Move;
import com.scaler.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private Map<Symbol, Integer> leftMap = new HashMap<>();
    private Map<Symbol, Integer> rightMap = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if (row == col) { // left diagonal
            if (!leftMap.containsKey(symbol)) {
                leftMap.put(symbol, 0);
            }
            leftMap.put(symbol, leftMap.get(symbol) + 1);

            return leftMap.get(symbol) == board.getDimension();
        }

        if (row + col == board.getDimension() - 1) { // right diagonal
            if (!rightMap.containsKey(symbol)) {
                rightMap.put(symbol, 0);
            }
            rightMap.put(symbol, rightMap.get(symbol) + 1);

            return rightMap.get(symbol) == board.getDimension();
        }

        return false;
    }
}
