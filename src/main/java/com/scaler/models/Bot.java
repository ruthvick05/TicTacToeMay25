package com.scaler.models;

import com.scaler.factory.BotPlayingStrategyFactory;
import com.scaler.strategies.botplayingstrategy.BotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy playingStrategy;

    public Bot(String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol);
        this.setPlayerType(PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.playingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }

    @Override
    public Move makeMove(Board board) {
        return playingStrategy.makeMove(board);
    }
}
