package upm.controller;

import upm.model.Player;
import upm.model.Tournament;

import java.time.LocalDate;
import java.util.ArrayList;

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
                output.append(tournament.rank()).append("\n");
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
            output.append(tournament.rank()).append("\n");
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
            output = tournament.matchmake(args);
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
}
