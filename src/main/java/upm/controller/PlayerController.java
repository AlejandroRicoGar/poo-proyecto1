package upm.controller;

import upm.model.Player;

import java.util.ArrayList;

public class PlayerController {
    private ArrayList<Player> players;

    public PlayerController() {
        players = new ArrayList<>();
    }

    public void addPlayer (Player player){
        players.add(player);
    }

    public Player search(String name){
        Player player = null;
        if(!players.isEmpty()){
            for(Player p : players){
                if(p.getName().equals(name)){
                    player = p;
                }
            }
        }
        return player;
    }
}
