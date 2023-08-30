package com.example.digits.dto;

import com.example.digits.model.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UnfinishedGame extends Game {
    @Override
    @JsonIgnore
    public char[] getDigits() {
        return super.getDigits();
    }
}
