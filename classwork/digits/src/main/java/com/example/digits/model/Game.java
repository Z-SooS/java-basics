package com.example.digits.model;

import lombok.Data;

import java.util.List;

@Data
public class Game {
    private int id;
    private char[] digits;
    private boolean finished;
    private List<Round> rounds;
}
