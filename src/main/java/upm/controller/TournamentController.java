package upm.controller;

import upm.model.Player;
import upm.model.Tournament;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class TournamentController {
    private ArrayList<Tournament> tournaments;
    public TournamentController() {
        tournaments = new ArrayList<>();
    }
    public void AddTournament(Tournament tournament) {
        tournaments.add(tournament);
    }
    public void DeleteTournament(Tournament tournament) {
        ArrayList<Player> jugadores = tournament.getPlayers();
        for(Player jugador : jugadores) {
            jugador.removeTournament(tournament);
        }
    }
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
    public String rank(){
        StringBuilder output = new StringBuilder();
        for (Tournament tournament : tournaments) {
            output.append(tournament.getName()).append("\n");
            output.append(tournament.rank()).append("\n");
        }
        return output.toString();
    }
    public String show(){
        StringBuilder output = new StringBuilder();
        for (Tournament tournament : tournaments) {
            output.append(tournament.getName()).append("\n");
            output.append(tournament.showPlayers()).append("\n");
        }
        return output.toString();
    }
}
