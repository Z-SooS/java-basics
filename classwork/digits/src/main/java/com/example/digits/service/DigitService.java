package com.example.digits.service;

import com.example.digits.dao.GameDao;
import com.example.digits.dao.RoundDao;
import com.example.digits.model.Game;
import com.example.digits.model.Round;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@EnableAsync
public class DigitService {
    public static final int NUMBER_OF_DIGITS = 4;
    private Random random;
    private GameDao gameDao;
    private RoundDao roundDao;

    public CompletableFuture<Integer> createNewGame() {
        return CompletableFuture.supplyAsync(() -> {
            Game generatedGame = new Game();
            generatedGame.setDigits(getNewDigits());
            generatedGame.setFinished(false);

            gameDao.addNewGame(generatedGame); // SQL

            return generatedGame.getId();
        });
    }

    private char[] getNewDigits() {
        char[] chars = new char[NUMBER_OF_DIGITS];
        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            chars[i] = (char) (random.nextInt(10) + '0');
        }
        return chars;
    }
    @Async
    public CompletableFuture<Round> makeGuess(int gameId, String guess) {
        return CompletableFuture.supplyAsync(()->{
            if (guess.length() != NUMBER_OF_DIGITS) throw new RuntimeException("Guesses should be 4 digits");

            Round createdRound = new Round();
            createdRound.setGuess(guess.toCharArray());
            createdRound.setGameId(gameId);

            Game game = gameDao.getGame(gameId);
            GuessStatisticCollector guessStatisticCollector = checkGuess(game.getDigits(), createdRound.getGuess());

            createdRound.setCorrect(guessStatisticCollector.correctGuess);
            createdRound.setPartial(guessStatisticCollector.partialGuess);

            createdRound = roundDao.addRound(createdRound);

            if (createdRound.getCorrect() == NUMBER_OF_DIGITS) finishGame(gameId);
            return createdRound;
        });
    }
    @Async
    public CompletableFuture<List<Game>> getAllGames() {
        return CompletableFuture.supplyAsync(()-> {
            List<Game> games = gameDao.getAllGames();
            games.forEach(game -> {
                game.setRounds(roundDao.getRoundsForGame(game.getId()));
            });

            return games;
        });
    }

    private void finishGame(int gameId) {
        gameDao.finishGame(gameId);
    }

    private static GuessStatisticCollector checkGuess(char[] gameDigits, char[] guessDigits) {
        if (gameDigits.length != guessDigits.length || gameDigits.length != NUMBER_OF_DIGITS) throw new RuntimeException(
                "Guesses should be " + NUMBER_OF_DIGITS + " digits\n" +
                        "Game digits: " + Arrays.toString(gameDigits) + "\n" +
                        "Guess digits: " + Arrays.toString(guessDigits)
        );
        Set<Character> characterSet = createSetOfDigits(gameDigits);

        GuessStatisticCollector guessStatisticCollector = new GuessStatisticCollector();

        for (int i = 0; i < gameDigits.length; i++) {
            if(gameDigits[i] == guessDigits[i]) guessStatisticCollector.correctGuess += 1;
            else if (characterSet.contains(guessDigits[i])) guessStatisticCollector.partialGuess += 1;
        }

        return guessStatisticCollector;
    }
    private static Set<Character> createSetOfDigits(char[] gameDigits) {
        Set<Character> characters = new HashSet<>();
        for (char digit : gameDigits) {
            characters.add(digit);
        }
        return characters;
    }

    private static class GuessStatisticCollector {
        public byte correctGuess = 0;
        public byte partialGuess = 0;
    }
}
