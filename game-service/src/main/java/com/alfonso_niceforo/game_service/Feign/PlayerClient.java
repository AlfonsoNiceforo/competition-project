package com.alfonso_niceforo.game_service.Feign;

import com.alfonso_niceforo.game_service.payload.Players;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "player-service", url = "${feign.player-service.url}")
public interface PlayerClient {

    @PostMapping("player-verification")
    public Boolean verify(@RequestBody Players players);
}
