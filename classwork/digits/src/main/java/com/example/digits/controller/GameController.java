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

import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
public class GameController {
    private DigitService service;

    @PostMapping("begin")
    public CompletableFuture<ResponseEntity<Integer>> begin() {
        service.createNewGame();
        return service.createNewGame().thenApply(id -> ResponseEntity.status(HttpStatus.CREATED).body(id));
    }

    @PostMapping("guess")
    public CompletableFuture<Round> makeGuess(@RequestBody IncomingGuess incomingGuess) {
        return service.makeGuess(incomingGuess.getGameId(), incomingGuess.getGuess());
    }
}
