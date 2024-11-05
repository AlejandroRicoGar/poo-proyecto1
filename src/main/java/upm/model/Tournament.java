package upm.model;

import upm.model.category.Categoria;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Tournament {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String league;
    private String sport;
    private Categorys categoria;
    private TournamentTypes type;
    private ArrayList<Player> players;
    private ArrayList<Team> teams;

    public Tournament(String name, String startDate, String endDate, String league, String sport, Categorys categoria,TournamentTypes type) {
        this.name = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.startDate = LocalDate.parse(startDate, formatter);
        this.endDate = LocalDate.parse(endDate, formatter);
        this.league = league;
        this.sport = sport;
        this.categoria = categoria;
        this.type = type;

        if(type.equals(TournamentTypes.INDIVIDUAL)){
            players = new ArrayList<Player>();
        }else{
            teams = new ArrayList<Team>();
        }
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    public String getName() {
        return name;
    }
    public void addPlayer(Player player) {
        players.add(player);
    }
    public void addTeam(Team team) {
        teams.add(team);
    }
    public String showPlayers(){
        StringBuilder builder = new StringBuilder();
        if(TournamentTypes.INDIVIDUAL.equals(type)){
            for(Player p : players){
                builder.append(p.getName()+" ");
            }
        }else{
            for(Team t : teams){
                builder.append(t.getName()+"  ");
            }
        }
        return builder.toString();
    }
    public String rank(){
        String output = "";
        if(type.equals(TournamentTypes.INDIVIDUAL)){
            output = rankPlayer();
        }else{
            output = rankTeam();
        }
        return output;
    }

    private String rankTeam(){
        StringBuilder output = new StringBuilder();
        ArrayList<Team> sortedPlayerList = new ArrayList<>(teams);
        sortedPlayerList.sort((Team p1, Team p2) -> p2.getCategory(categoria).compareTo(p1.getCategory(categoria)));
        for (Team team : sortedPlayerList) {
            output.append(team.getName() +" "+team.getCategory(categoria)+ "\n");
        }
        return output.toString();

    }
    private String rankPlayer(){
        StringBuilder output = new StringBuilder();
        ArrayList<Player> sortedPlayerList = new ArrayList<>(players);
        sortedPlayerList.sort((Player p1, Player p2) -> p2.getCategory(categoria).compareTo(p1.getCategory(categoria)));
        for (Player player : sortedPlayerList) {
            output.append(player.getName() +" "+player.getCategory(categoria)+"\n");
        }
        return output.toString();
    }


}
