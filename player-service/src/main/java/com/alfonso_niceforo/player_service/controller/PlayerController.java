package com.alfonso_niceforo.player_service.controller;

import com.alfonso_niceforo.player_service.payload.Players;
import com.alfonso_niceforo.player_service.payload.PlayersUsage;
import com.alfonso_niceforo.player_service.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;


    @GetMapping
    public ResponseEntity<List<PlayersUsage>> getCharacters() {
        List<PlayersUsage> list = playerService.prendiTuttiIGiocatori();
        return ResponseEntity.ok(list);
    }


    @PostMapping("player-verification")
    public Boolean verify(@RequestBody Players players) {
        return playerService.verificaPresenzaGiocatori(players);
    }
}
