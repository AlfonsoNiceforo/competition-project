package com.alfonso_niceforo.player_service.controller;

import com.alfonso_niceforo.player_service.payload.Players;
import com.alfonso_niceforo.player_service.payload.PlayersUsage;
import com.alfonso_niceforo.player_service.service.PlayerService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Player Controller", description = "")
public class PlayerController {

    @Autowired
    private PlayerService playerService;


    @Operation(
            summary = "Ritorna i giocatori",
            description = "Restituisce lista di giocatori e percentuale di partecipazione",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista giocatori restituita",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PlayersUsage.class)))),
                    @ApiResponse(responseCode = "500", description = "Errore interno del server")
            }
    )
    @GetMapping
    public ResponseEntity<List<PlayersUsage>> getCharacters() {
        List<PlayersUsage> list = playerService.prendiTuttiIGiocatori();
        return ResponseEntity.ok(list);
    }

    @Hidden
    @PostMapping("player-verification")
    public Boolean verify(@RequestBody Players players) {
        return playerService.verificaPresenzaGiocatori(players);
    }
}
