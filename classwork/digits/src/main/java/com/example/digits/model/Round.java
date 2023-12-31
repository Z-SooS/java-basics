package com.example.digits.model;

import lombok.Data;

@Data
public class Round {
    private int id;
    private int gameId;
    private char[] guess;
    private byte correct;
    private byte partial;
}
