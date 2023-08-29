package com.example.digits.dao;

import com.example.digits.dto.UnfinishedGame;
import com.example.digits.model.Game;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class GameDaoDb implements GameDao{
    private JdbcTemplate jdbc;
    @Override
    public Game getGame(int gameId) {
        try {
            final String GET_GAME = "SELECT * FROM game WHERE id = ?";
            return jdbc.queryForObject(GET_GAME, new GameMapper(), gameId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        final String GET_GAMES = "SELECT * FROM game";
        return jdbc.query(GET_GAMES, new GameMapper());
    }

    @Override
    @Transactional
    public Game addNewGame(Game addedGame) {
        final String ADD_NEW_GAME = "INSERT INTO game(digits) VALUES (?)";
        jdbc.update(ADD_NEW_GAME, new String(addedGame.getDigits()));

        final String GET_ID = "SELECT currval('game_id_seq')";
        addedGame.setId(jdbc.queryForObject(GET_ID, Integer.class));
        return addedGame;
    }

    @Override
    @Transactional
    public void deleteGame(int gameId) {
        final String DELETE_ROUNDS = "DELETE FROM round WHERE game_id = ?";
        jdbc.update(DELETE_ROUNDS, gameId);

        final String DELETE_GAME = "DELETE FROM game WHERE id = ?";
        jdbc.update(DELETE_GAME, gameId);
    }

    @Override
    public void finishGame(int gameId) {
        final String UPDATE_GAME =
                "UPDATE game SET finished = true WHERE id = ?";
        jdbc.update(UPDATE_GAME, gameId);
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            boolean isFinished = rs.getBoolean("finished");
            Game game = isFinished ? new Game() : new UnfinishedGame();
            game.setId(rs.getInt("id"));
            game.setDigits(rs.getString("digits").toCharArray());
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }
}
