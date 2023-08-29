package com.example.digits.dao;

import com.example.digits.model.Round;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Repository
public class RoundDaoDb implements RoundDao{
    private JdbcTemplate jdbc;

    @Override
    public Round getRound(int roundId) {
        try {
            final String GET_ROUND = "SELECT * FROM round WHERE id = ?";
            return jdbc.queryForObject(GET_ROUND, new RoundMapper(), roundId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Round> getRoundsForGame(int gameId) {
        final String GET_ROUNDS = "SELECT * FROM round WHERE game_id = ?";
        return jdbc.query(GET_ROUNDS, new RoundMapper(), gameId);
    }

    @Override
    @Transactional
    public Round addRound(Round addedRound) {
        final String INSERT_ROUND = "INSERT INTO round(game_id, guess) VALUES(?,?)";
        jdbc.update(INSERT_ROUND, addedRound.getGame_id(), addedRound.getGuess());

        final String GET_ID = "SELECT currval('round_id_seq')";
        //noinspection DataFlowIssue
        addedRound.setId(jdbc.queryForObject(GET_ID, Integer.class));
        return addedRound;
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int rowNum) throws SQLException {
            Round get = new Round();
            get.setId(rs.getInt("id"));
            get.setGuess(rs.getString("guess").toCharArray());
            get.setGame_id(rs.getInt("game_id"));
            return get;
        }
    }
}