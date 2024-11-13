package upm.controller;

import upm.model.Admin;
import upm.model.Categories;
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

    public String getStatisticsCSV(Player player) {
        String output;
        if (player != null) {
            String format = "%-20s%-20s%-20s%-20s%-20s%n";
            String header = String.format(format, "Scored Points", "Match Won", "Assists Points", "Past Tournaments", "Generated Money");
    
            double scoredPoints = player.getCategoryValue(Categories.SCORED_POINTS);
            double matchWon = player.getCategoryValue(Categories.MATCH_WON);
            double assistsPoints = player.getCategoryValue(Categories.ASISTS_POINTS);
            double pastTournaments = player.getCategoryValue(Categories.PAST_TOURNAMENTS);
            double generatedMoney = player.getCategoryValue(Categories.GENERATED_MONEY);
    
            String data = String.format(format, 
                                        String.format("%.2f", scoredPoints), 
                                        String.format("%.2f", matchWon), 
                                        String.format("%.2f", assistsPoints), 
                                        String.format("%.2f", pastTournaments), 
                                        String.format("%.2f", generatedMoney));
    
            output = header + data;
        } else {
            output = "The player does not exist";
        }
        return output;
    }
    
    public String getStatisticsJSON(Player player) {
        String output;
        if (player != null) {
            output = "Scored Points: " + String.format("%.2f", player.getCategoryValue(Categories.SCORED_POINTS)) + ",\n" +
                 "Match Won: " + String.format("%.2f", player.getCategoryValue(Categories.MATCH_WON)) + ",\n" +
                 "Assists Points: " + String.format("%.2f", player.getCategoryValue(Categories.ASISTS_POINTS)) + ",\n" +
                 "Past Tournaments: " + String.format("%.2f", player.getCategoryValue(Categories.PAST_TOURNAMENTS)) + ",\n" +
                 "Generated Money: " + String.format("%.2f", player.getCategoryValue(Categories.GENERATED_MONEY));
        } else {
            output = "The player does not exist";
        }
        return output;
    }
}
