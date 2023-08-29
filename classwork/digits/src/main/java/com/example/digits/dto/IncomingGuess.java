package com.example.digits.dto;

import com.example.digits.model.Round;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class IncomingGuess {
    private int gameId;
    @Size(max = 4)
    private String guess;
}
