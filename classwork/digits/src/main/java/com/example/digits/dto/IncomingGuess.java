package com.example.digits.dto;

import com.example.digits.service.DigitService;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class IncomingGuess {
    private int gameId;
    @Size(max = DigitService.NUMBER_OF_DIGITS)
    private String guess;
}
