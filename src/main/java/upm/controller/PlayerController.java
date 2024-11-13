package upm.controller;

import upm.model.Admin;
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

    public String createPlayer(String name, String surname, String id, String password, String email, Admin creator) {
        String output = "";
        boolean alreadyExists = false;
        for (Player player : players) {
            if (player.getId().equals(id)) {
                output = "  Player with ID " + id + " already exists";
                alreadyExists = true;
                break;
            }
        }
        if (!alreadyExists) {
            Player player = new Player(name, surname, id, password, email, creator);
            players.add(player);
            output = "  Player " + name + " created successfully";
        }
        return output;
    }
}
