package upm.controller;

import upm.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TournamentController {
    private ArrayList<Tournament> tournaments;
    private PublicController publicController;

    public TournamentController() {
        tournaments = new ArrayList<>();
    }

    /**
     * @param tournament Tournament to be added to the tournaments list
     */
    public void AddTournament(Tournament tournament) {
        tournaments.add(tournament);
    }

    /**
     * Deletes the tournament and in the players list
     * @param tournament Tournament that is going to be deleted
     */
    public void DeleteTournament(Tournament tournament) {
        ArrayList<Player> jugadores = tournament.getPlayers();
        for(Player jugador : jugadores) {
            jugador.removeTournament(tournament);
        }
    }

    /**
     * Ranks the players of all tournmanets and deletes the tournaments which endDate is before actualDate
     * @return String of the name of all tournament and its ranked members
     */
    public String rankAndPrune(){
        LocalDate date = LocalDate.now();
        StringBuilder output = new StringBuilder();
        for (Tournament tournament : tournaments) {
            if(tournament.getEndDate().isBefore(date)){
                DeleteTournament(tournament);
            }else{
                output.append(tournament.getName()).append("\n");
                output.append(rankAux(tournament)).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Ranks the players of all tournaments
     * @return String of the name of all tournament and its ranked members
     */
    public String rank(){
        StringBuilder output = new StringBuilder();
        for (Tournament tournament : tournaments) {
            output.append(tournament.getName()).append("\n");
            output.append(rankAux(tournament)).append("\n");
        }
        return output.toString();
    }

    /**
     * Shows all tournaments and its members without ranking them
     * @return String of the name of all tournament and its  members
     */
    public String show(){
        StringBuilder output = new StringBuilder();
        for (Tournament tournament : tournaments) {
            output.append(tournament.getName()).append("\n");
            output.append(tournament.showMembers()).append("\n");
        }
        return output.toString();
    }

    public String manualMatchmaking(String[] args){
        String output = "";
        Tournament tournament = search(args[0]);
        if(tournament != null){
            output = matchmakeAux(args,tournament);
        }else{
            output = "The tournament "+args[0]+" doesn't exist";
        }
        return output;
    }

    public String autoMatchmaking(String[] args){
        String output = "";;
        Tournament tournament = search(args[0]);
        if(tournament != null){
            output = autoMatchmakeAux(tournament);
        }else{
            output = "The tournament "+args[0]+" doesn't exist";
        }
        return output;
    }

    public Tournament search(String name){
        for (Tournament tournament : tournaments) {
            if(tournament.getName().equals(name)){
                return tournament;
            }
        }
        return null;
    }
    private String rankAux(Tournament tournament){
        String output = "";
        if(tournament.getType().equals(TournamentTypes.INDIVIDUAL)){
            output = rankPlayer(tournament);
        }else{
            output = rankTeam(tournament);
        }
        return output;
    }

    private String rankTeam(Tournament tournament){
        StringBuilder output = new StringBuilder();
        ArrayList<Team> sortedPlayerList = new ArrayList<>(tournament.getTeams());
        sortedPlayerList.sort((Team p1, Team p2) -> p2.getCategory(tournament.getCategoria()).compareTo(p1.getCategory(tournament.getCategoria())));
        for (Team team : sortedPlayerList) {
            output.append(team.getName() +" "+team.getCategory(tournament.getCategoria())+ "\n");
        }
        return output.toString();

    }
    private String rankPlayer(Tournament tournament){
        StringBuilder output = new StringBuilder();
        ArrayList<Player> sortedPlayerList = new ArrayList<>(tournament.getPlayers());
        sortedPlayerList.sort((Player p1, Player p2) -> p2.getCategory(tournament.getCategoria()).compareTo(p1.getCategory(tournament.getCategoria())));
        for (Player player : sortedPlayerList) {
            output.append(player.getName() +" "+player.getCategory(tournament.getCategoria())+"\n");
        }
        return output.toString();
    }


    public String matchmakeAux(String[] args,Tournament tournament){
        if(tournament.getType().equals(TournamentTypes.INDIVIDUAL)){
            return matchmakePlayer(args,tournament);
        }else{
            return matchmakeTeam(args,tournament);
        }
    }

    public String autoMatchmakeAux(Tournament tournament){
        if(tournament.getType().equals(TournamentTypes.INDIVIDUAL)){
            return autoMatchmakePlayer(tournament);
        }else{
            return autoMatchmakeTeam(tournament);
        }
    }


    private String autoMatchmakePlayer(Tournament tournament){
        String output = "";
        if(tournament.getPlayers().size()%2==0) {
            List<Player> playerCopy = new ArrayList<>(tournament.getPlayers());
            Collections.shuffle(playerCopy);
            for (int i = 0; i < tournament.getPlayers().size(); i += 2) {
                Player[] players = {playerCopy.get(i), playerCopy.get(i + 1)};
                Matchmaking m = new Matchmaking(players[0], players[1]);
                tournament.getMatches().add(m);
            }
            output = showMatchmakes(tournament);
        }else{
            output = "There are not an even number of players in this tournament";
        }
        return output;
    }

    private String autoMatchmakeTeam(Tournament tournament){
        String output = "";
        if(tournament.getTeams().size()%2==0) {
            List<Team> teamCopy = new ArrayList<>(tournament.getTeams());
            Collections.shuffle(teamCopy);
            for (int i = 0; i < tournament.getTeams().size(); i += 2) {
                Team[] teams = {teamCopy.get(i), teamCopy.get(i + 1)};
                Matchmaking m = new Matchmaking(teams[i], teams[i + 1]);
                tournament.getMatches().add(m);
            }
            output = showMatchmakes(tournament);
        }else{
            output = "There are not an even number of players in this tournament";
        }
        return output;
    }

    private String matchmakePlayer(String[] args,Tournament tournament){
        if(args.length-1 == tournament.getPlayers().size()){
            for(int i = 1;i<args.length;i+=2){
                Player p1 = tournament.searchPlayer(args[i]);
                Player p2 = tournament.searchPlayer(args[i+1]) ;
                if(p1!=null && p2!= null){
                    Matchmaking m = new Matchmaking(p1,p2);
                    tournament.getMatches().add(m);
                }else{
                    return "One of the players does not exist or is not in the tournament";
                }
            }
        }
        return showMatchmakes(tournament);
    }
    private String matchmakeTeam(String[] args,Tournament tournament){
        if(args.length-1 == tournament.getTeams().size()){
            for(int i = 1;i<args.length;i+=2){
                Team t1 = tournament.searchTeam(args[i]);
                Team t2 = tournament.searchTeam(args[i+1]);
                if(t1!=null && t2 != null){
                    Matchmaking m = new Matchmaking(t1,t2);
                    tournament.getMatches().add(m);
                }else{
                    return "One of the teams does not exist or is not in the tournament";
                }
            }
        }
        return showMatchmakes(tournament);
    }

    private String showMatchmakes(Tournament tournament){
        StringBuilder builder = new StringBuilder();
        for(Matchmaking m : tournament.getMatches()){
            builder.append(m.toString()).append("\n");
        }
        return builder.toString();
    }


}
