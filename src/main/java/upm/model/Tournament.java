package upm.model;

import java.sql.SQLOutput;
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
    private ArrayList<Matchmaking> matches;

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

        matches = new ArrayList<Matchmaking>();
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

    public TournamentTypes getType() {
        return type;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Matchmaking> getMatches() {
        return matches;
    }

    public Categorys getCategoria() {
        return categoria;
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

    public Player searchPlayer(String name) {
        Iterator<Player> iter = players.iterator();
        boolean exists = false;
        Player player = null;
        while (!exists && iter.hasNext()) {
            Player aux = iter.next();
            if (aux.getName().equals(name)) {
                player = aux;
                exists = true;
            }
        }
        return player;
    }

    public Team searchTeam(String name) {
        Iterator<Team> iter = teams.iterator();
        boolean exists = false;
        Team t = null;
        while (!exists && iter.hasNext()) {
            Team aux = iter.next();
            if (aux.getName().equals(name)) {
                t = aux;
                exists = true;
            }
        }
        return t;
    }



}
