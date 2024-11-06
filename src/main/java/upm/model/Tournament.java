package upm.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    /**
     * @param name Name of the tournament
     * @param startDate Date of the begging of the tournament
     * @param endDate  Date of the end of the tournament
     * @param league    League of the tournament
     * @param sport Sport that is played in the tournament
     * @param categoria Category that is used to rank in the tournament
     * @param type Enum that says if its individual or colletive
     */
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

    /**
     * @param player player to be added to the tournament
     */
    public void addPlayer(Player player) {
        if(type.equals(TournamentTypes.INDIVIDUAL)){
            players.add(player);
        }
    }

    /**
     * @param team team to be added to the tournament
     */
    public void addTeam(Team team) {
        if(type.equals(TournamentTypes.COLECTIVO)){
            teams.add(team);
        }
    }

    /**
     * @return all the members of a tournament
     */
    public String showMembers(){
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

    /**
     * @return ranks the members of the tournaments
     */
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
