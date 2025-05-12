package com.scaler.strategies.winningstrategy;

import com.scaler.models.Board;
import com.scaler.models.Move;
import com.scaler.models.Player;
import com.scaler.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol, Integer>> rowMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if (!rowMaps.containsKey(row)) {
            rowMaps.put(row, new HashMap<>());
        }

        Map<Symbol, Integer> currentRowMap = rowMaps.get(row);

        if (!currentRowMap.containsKey(symbol)) {
            currentRowMap.put(symbol, 0);
        }

        currentRowMap.put(symbol, currentRowMap.get(symbol) + 1);

        return currentRowMap.get(symbol) == board.getDimension();
    }
}
