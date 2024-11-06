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

    public void addMatch(Matchmaking matchmaking) {
        matches.add(matchmaking);
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

    public String matchmake(String[] args){
        if(type.equals(TournamentTypes.INDIVIDUAL)){
            return matchmakePlayer(args);
        }else{
            return matchmakeTeam(args);
        }
    }

    public String autoMatchmake(){
        if(type.equals(TournamentTypes.INDIVIDUAL)){
            return autoMatchmakePlayer();
        }else{
            return autoMatchmakeTeam();
        }
    }


    private String autoMatchmakePlayer(){
        String output = "";
        if(players.size()%2==0) {
            List<Player> playerCopy = new ArrayList<>(players);
            Collections.shuffle(playerCopy);
            for (int i = 0; i < players.size(); i += 2) {
                Player[] players = {playerCopy.get(i), playerCopy.get(i + 1)};
                Matchmaking m = new Matchmaking(players[0], players[1]);
                matches.add(m);
            }
            output = showMatchmakes();
        }else{
            output = "There are not an even number of players in this tournament";
        }
        return output;
    }

    private String autoMatchmakeTeam(){
        String output = "";
        if(teams.size()%2==0) {
            List<Team> teamCopy = new ArrayList<>(teams);
            Collections.shuffle(teamCopy);
            for (int i = 0; i < teams.size(); i += 2) {
                Team[] teams = {teamCopy.get(i), teamCopy.get(i + 1)};
                Matchmaking m = new Matchmaking(teams[i], teams[i + 1]);
                matches.add(m);
            }
            output = showMatchmakes();
        }else{
            output = "There are not an even number of players in this tournament";
        }
        return output;
    }

    private String matchmakePlayer(String[] args){
        if(args.length-1 == players.size()){
            for(int i = 1;i<args.length;i+=2){

                Player p1 = searchPlayer(args[i]);
                Player p2 = searchPlayer(args[i+1]) ;
                if(p1!=null && p2!= null){
                    Matchmaking m = new Matchmaking(p1,p2);
                    matches.add(m);
                }else{
                    return "One of the players does not exist or is not in the tournament";
                }
            }
        }
        return showMatchmakes();
    }
    private String matchmakeTeam(String[] args){
        if(args.length-1 == teams.size()){
            for(int i = 1;i<args.length;i+=2){
                Team t1 = searchTeam(args[i]);
                Team t2 = searchTeam(args[i+1]);
                if(t1!=null && t2 != null){
                    Matchmaking m = new Matchmaking(t1,t2);
                    matches.add(m);
                }else{
                    return "One of the teams does not exist or is not in the tournament";
                }
            }
        }
        return showMatchmakes();
    }

    private Player searchPlayer(String name) {
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

    private Team searchTeam(String name) {
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

    private String showMatchmakes(){
        StringBuilder builder = new StringBuilder();
        for(Matchmaking m : matches){
            builder.append(m.toString()).append("\n");
        }
        return builder.toString();
    }


}
