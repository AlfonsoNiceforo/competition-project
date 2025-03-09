package com.alfonso_niceforo.game_service.controller;

import com.alfonso_niceforo.game_service.payload.GameEvent;
import com.alfonso_niceforo.game_service.payload.Players;
import com.alfonso_niceforo.game_service.Feign.PlayerClient;
import com.alfonso_niceforo.game_service.service.MessageProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final MessageProducerService messageProducerService;
    private final PlayerClient playerClient;

    public GameController(MessageProducerService messageProducerService, PlayerClient playerClient) {
        this.messageProducerService = messageProducerService;
        this.playerClient = playerClient;
    }

    @PostMapping
    ResponseEntity<String> postGame(@RequestBody GameEvent gameEvent) {
        Players players = new Players(gameEvent.getPlayerName1(), gameEvent.getPlayerName2());
        if (playerClient.verify(players)) {
            try {
                messageProducerService.sendMessage(gameEvent);
                return ResponseEntity.ok("Game request accepted");
            } catch (JsonProcessingException e) {
                return ResponseEntity.internalServerError().body("Errore nell'invio: " + e.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("Players are not valid");
    }
}
