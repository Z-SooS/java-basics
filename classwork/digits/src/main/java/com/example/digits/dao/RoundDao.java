package com.example.digits.dao;

import com.example.digits.model.Round;

import java.util.List;

public interface RoundDao {
    Round getRound(int roundId);
    List<Round> getRoundsForGame(int gameId);

    Round addRound(Round addedRound);
}
