package upm.controller;

import upm.model.Admin;
import upm.model.Categories;
import upm.model.Player;

import java.util.ArrayList;

public class PlayerController {
    private ArrayList<Player> players;
    private static PlayerController instance;

    private PlayerController() {
        players = new ArrayList<>();
    }

    public static PlayerController getInstance() {
        if (instance == null) {
            instance = new PlayerController();
        }
        return instance;
    }
    public void addPlayer (Player player){
        players.add(player);
    }

    public Player search(String id){
        Player player = null;
        if(!players.isEmpty()){
            for(Player p : players){
                if(p.getId().equals(id)){
                    player = p;
                }
            }
        }
        return player;
    }

    public String deletePlayer(Player player){
        players.remove(player);
        return "  Player " + player.getName() + " deleted successfully";
    }


    public String createPlayer(String name, String surname, String password, String email, Admin creator) {
        Player player = new Player(name, surname, password, email, creator);
        players.add(player);
        PublicController.getInstance().signUpUser(player);

        return "Player " + name + " created successfully";
    }
    public Player searchMail(String email){
        Player player = null;
        if(!players.isEmpty()){
            for(Player p : players){
                if(p.getMail().equals(email)){
                    player = p;
                }
            }
        }
        return player;
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
