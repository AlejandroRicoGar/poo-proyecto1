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

    public String deletePlayer(String name){
        String output = "";
        Player player = search(name);
        if(player != null) {
            if (player.getTournaments().isEmpty()) {
                players.remove(player);
                output = "  Player " + name + " deleted successfully";
            } else {
                output = "  The player " + name + " is in a tournament, you must remove the player from the tournament before deleting it";
            }
        }
        return output;
    }

}
