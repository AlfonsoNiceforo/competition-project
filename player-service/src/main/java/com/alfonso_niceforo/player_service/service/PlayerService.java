package com.alfonso_niceforo.player_service.service;

import com.alfonso_niceforo.player_service.DAO.PlayerDAO;
import com.alfonso_niceforo.player_service.payload.Players;
import com.alfonso_niceforo.player_service.payload.PlayersUsage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {
    private final PlayerDAO playerDAO;

    public PlayerService(PlayerDAO playerDAO) {
        this.playerDAO=playerDAO;
    }

    public List<PlayersUsage> prendiTuttiIGiocatori() {
        return playerDAO.getAllCharacters();
    }

    public Set<String> daiTuttiINomi() {
        return playerDAO.getAllCharacterNames();
    }

    public boolean verificaPresenzaGiocatori(Players players) {
        Set<String> nomi = new HashSet<>();
        nomi.add(players.getPlayerName1());
        nomi.add(players.getPlayerName2());
        return daiTuttiINomi().containsAll(nomi);
    }

    /*
    public boolean verifyCharacters(Players players) {
        Set<String> listaNomi = daiTuttiINomi();
        System.out.println();
        System.out.println("Lista nomi dal DB: " + listaNomi);
        System.out.println("Nome 1: " + players.getPlayerName1()+ ", Nome 2: " + players.getPlayerName2());
        List<Map<String, Boolean>> res = new ArrayList<>();
        res.add(new HashMap<String, Boolean>());
        res.add(new HashMap<String, Boolean>());
        res.get(0).put(players.getPlayerName1(), false);
        res.get(1).put(players.getPlayerName2(), false);

        System.out.println("Mappa: " + res);

        //System.out.println("È true? : " + "Mappa1: " + res.get(0).get(0) + " = " + charactersRequestDTO.getCharacterName1());
        //System.out.println(res.get(0).get(0).equals(charactersRequestDTO.getCharacterName1()));

        for (String s : listaNomi) {
            if (players.getPlayerName1().equals(s) || players.getPlayerName2().equals(s)) {
                for (Map<String, Boolean> m : res) {
                    if (m.containsKey(s)) {
                        m.put(s, true);
                    }
                }
            }
        }

        System.out.println("Mappa dopo verifica: " + res);

        return res.get(0).containsValue(true) && res.get(1).containsValue(true);
    }
    */
}
