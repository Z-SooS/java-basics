package com.example.digits.controller;

import com.example.digits.dto.IncomingGuess;
import com.example.digits.model.Game;
import com.example.digits.model.Round;
import com.example.digits.service.DigitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
public class GameController {
    private DigitService service;

        //"rounds/{gameId} – GET – Returns a list of rounds for the specified game sorted by time.

    @PostMapping("begin")
    public CompletableFuture<ResponseEntity<Integer>> begin() {
        service.createNewGame();
        return service.createNewGame().thenApply(id -> ResponseEntity.status(HttpStatus.CREATED).body(id));
    }

    @PostMapping("guess")
    public CompletableFuture<Round> makeGuess(@RequestBody IncomingGuess incomingGuess) {
        return service.makeGuess(incomingGuess.getGameId(), incomingGuess.getGuess());
    }
    @GetMapping("game")
    public CompletableFuture<List<Game>> games() {
        return service.getAllGames();
    }

    @GetMapping("game/{gameId}")
    public CompletableFuture<ResponseEntity<Game>> getGame(@PathVariable int gameId) {
        return service.getGame(gameId).thenApply(ResponseEntity::ok).exceptionally(ex->{
            System.out.println("WHAT");
            if (ex instanceof NullPointerException) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            throw new RuntimeException(ex);
        });
    }

    @GetMapping("rounds/{gameId}")
    public CompletableFuture<List<Round>> getRoundsForGame(@PathVariable int gameId) {
        return service.getRoundsForGame(gameId);
    }
}
