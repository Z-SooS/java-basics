package com.example.digits.dao;

import com.example.digits.model.Game;

import java.util.List;

public interface GameDao {
    Game getGame(int gameId);
    List<Game> getAllGames();
    Game addNewGame(Game addedGame);
    void deleteGame(int gameId);
    void finishGame(int gameId);
}
