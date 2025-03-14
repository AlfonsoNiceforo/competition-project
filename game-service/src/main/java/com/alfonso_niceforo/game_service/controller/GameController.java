package com.alfonso_niceforo.game_service.controller;

import com.alfonso_niceforo.game_service.payload.GameEvent;
import com.alfonso_niceforo.game_service.payload.Players;
import com.alfonso_niceforo.game_service.Feign.PlayerClient;
import com.alfonso_niceforo.game_service.service.MessageProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Game Controller", description = "Gestisce l'invio della partita")
public class GameController {

    private final MessageProducerService messageProducerService;
    private final PlayerClient playerClient;

    public GameController(MessageProducerService messageProducerService, PlayerClient playerClient) {
        this.messageProducerService = messageProducerService;
        this.playerClient = playerClient;
    }

    @Operation(
            summary = "Inserisce una partita",
            description = "Manda una richiesta di inserzione di una partita dopo aver verificato i giocatori",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Game accepted",
                            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", description = "Errore interno del server")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = """
                                            {
                                            "playerName1" : "Maurizio",
                                            "playerName2" : "Pino",
                                            "time" : 120
                                            }
                                            """
                            )
                    )
            )
    )
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
