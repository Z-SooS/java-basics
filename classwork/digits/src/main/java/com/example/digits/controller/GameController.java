package com.example.digits.controller;

import com.example.digits.dto.IncomingGuess;
import com.example.digits.model.Round;
import com.example.digits.service.DigitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GameController {
    private DigitService service;

    @PostMapping("begin")
    public ResponseEntity<Integer> begin() {
        int gameId = service.createNewGame();
        return ResponseEntity.status(HttpStatus.CREATED).body(gameId);
    }

    @PostMapping("guess")
    public Round makeGuess(@RequestBody IncomingGuess incomingGuess) {
        return service.makeGuess(incomingGuess.getGameId(), incomingGuess.getGuess());
    }
}
