package com.alfonso_niceforo.leaderboard_service.controller;

import com.alfonso_niceforo.leaderboard_service.DAO.LeaderboardDAO;
import com.alfonso_niceforo.leaderboard_service.payload.GameEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Leaderboard Controller", description = "")
public class LeaderboardController {
    @Autowired
    private LeaderboardDAO leaderboardDao;

    @Operation(
            summary = "Ritorna la leaderboard",
            description = "Restituisce la leaderboard in ordine crescente dei migliori tempi",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Leaderboard restituita",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = GameEvent.class)))),
                    @ApiResponse(responseCode = "500", description = "Errore interno del server")
            }
    )
    @GetMapping
    public ResponseEntity<List<GameEvent>> getLeaderboard() {
        return ResponseEntity.ok(leaderboardDao.getLeaderboard());
    }
}
