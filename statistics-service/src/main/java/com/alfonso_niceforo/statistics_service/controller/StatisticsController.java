package com.alfonso_niceforo.statistics_service.controller;

import com.alfonso_niceforo.statistics_service.payload.Players;
import com.alfonso_niceforo.statistics_service.payload.Team;
import com.alfonso_niceforo.statistics_service.payload.TeamUsage;
import com.alfonso_niceforo.statistics_service.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Statistics Controller", description = "")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Operation(
            summary = "Ritorna le statistiche di tutti i giocatori",
            description = "Restituisce le statistiche degli accoppiamenti di tutti i giocatori",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Statistiche restituite",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Team.class)))),
                    @ApiResponse(responseCode = "500", description = "Errore interno del server")
            }
    )
    @GetMapping
    public ResponseEntity<List<Team>> getAllData() {
        List<Team> res = statisticsService.getAllCharactersData();
        return ResponseEntity.ok(res);
    }

    @Operation(
            summary = "Ritorna le statistiche del team",
            description = "Restituisce statistiche relative ad una coppia di giocatori",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Statistiche restituite",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TeamUsage.class))),
                    @ApiResponse(responseCode = "500", description = "Errore interno del server")
            }
    )
    @GetMapping("/team")
    public ResponseEntity<TeamUsage> getTeamData(@Parameter(example = "Maurizio") @RequestParam String playerName1,
                                                 @Parameter(example = "Gianluca") @RequestParam String playerName2) {
        TeamUsage res = statisticsService.getCharactersData(playerName1, playerName2);
        return ResponseEntity.ok(res);
    }

    @Operation(
            summary = "Ritorna le statistiche del giocatore",
            description = "Restituisce dati relativi agli accoppiamenti di un giocatore",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Statistiche restituite",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Team.class)))),
                    @ApiResponse(responseCode = "500", description = "Errore interno del server")
            }
    )
    @GetMapping("/{name}")
    public ResponseEntity<List<Team>> getCharacterData(
            @Parameter(description = "Nome giocatore", example = "Maurizio")
            @PathVariable String name) {
        List<Team> res =  statisticsService.getCharacterStatistics(name);
        return ResponseEntity.ok(res);
    }
}
