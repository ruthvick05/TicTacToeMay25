package com.scaler.factory;

import com.scaler.models.BotDifficultyLevel;
import com.scaler.strategies.botplayingstrategy.BotPlayingStrategy;
import com.scaler.strategies.botplayingstrategy.EasyBotPlayingStrategy;
import com.scaler.strategies.botplayingstrategy.HardBotPlayingStrategy;
import com.scaler.strategies.botplayingstrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel difficultyLevel) {
        if (difficultyLevel == BotDifficultyLevel.EASY) {
            return new EasyBotPlayingStrategy();
        } else if (difficultyLevel == BotDifficultyLevel.MEDIUM) {
            return new MediumBotPlayingStrategy();
        } else if (difficultyLevel == BotDifficultyLevel.HARD) {
            return new HardBotPlayingStrategy();
        }

        return null;
    }
}
