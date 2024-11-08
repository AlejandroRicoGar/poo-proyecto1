package upm.controller;

import upm.model.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TournamentController {
    private ArrayList<Tournament> tournaments;

    public TournamentController() {
        tournaments = new ArrayList<>();
    }

    /**
     * @param tournament Tournament to be added to the tournaments list
     */
    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
    }

    public String addMember(Tournament t,Player p) {
        String output = "";
        if(t != null){
                t.addMember(p);
                output =    "   Player with email "+p.getName()+" added to tournament"+t.getName();
        }else{
            output = "  The tournament does not exist";
        }
        return output;

    }

    public String addTeam(String tournamentName,Team team,Player p){
        Tournament t = search(tournamentName);
        String output = "";
        if(t != null) {
            if(team != null) {
                if(team.isMember(p)) {
                        t.addMember(team);
                        output = "  Team " + team.getName() + " added to tournament " + tournamentName;
                }else{
                    output = " You are not part of this team";
                }
            }else{
                output = "  The inserted team does not exists";
            }
        }else{
            output = "  The tournament "+tournamentName+" does not exists.";
        }
        return output;
    }

    public String createTournament(String[] args) {
        String output = "";
        if(search(args[0])==null){
            String[] espacios = args[0].split(" ");
            if(espacios.length==1){
                LocalDate startDate = null;
                LocalDate endDate = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try{
                    startDate = LocalDate.parse(args[1], formatter);
                    endDate = LocalDate.parse(args[2], formatter);
                    try{
                        Categorys cat = Categorys.valueOf(args[5]);
                        Tournament tournament = new Tournament(args[0],startDate,endDate,args[3],args[4],cat);
                        tournaments.add(tournament);
                        output = "  "+args[0]+" is going to be celebrated from "+startDate.toString()+" to " +endDate.toString()+", "
                                +args[3]+" is going to be played and is going to be ranked by  "+cat;
                    }catch (IllegalArgumentException e){
                        output = "  The category or the type are not ones of the permmited ";
                    }
                }catch (DateTimeParseException e){
                    output = "  The format of the date is not correct it must be DD/MM/YYYY";
                }
            }else{
                output = "  The name has blank spaces in it";
            }
        }else{
            output = "  The tournament "+args[0]+" already exists";
        }
        return output;
    }

    public String delete(String name){
        String output = "";
        Tournament tournament = search(name);
        if(tournament!=null){
            output = "  "+name+" eliminado correctamente";
            ArrayList<Member> jugadores = tournament.getMembers();
            if (jugadores.size() > 0) {
                for (Member jugador : jugadores) {
                    jugador.deleteTournament(tournament);
                }
            }
            tournaments.remove(tournament);
        }else{
            output = "  The tournament "+name+" does not exist";
        }
        return output;
    }

    public String removeTeam(Tournament t,String teamName,Player p){
        String output = "";
        if(t != null) {
            if (t.searchMember(teamName) != null) {
                Member m = t.searchMember(teamName);
                Team team = (Team)m;
                if(team.isMember(p)) {
                    output = "  Team " + teamName + " removed from Tournament " + t.getName();
                    t.deleteTeam(team);
                }else{
                    output = "You are not part of this team";
                }
            } else {
                output = "  The team " + teamName + " does not exists";
            }
        }else{
            output = "  The tournament does not exists";
        }
        return output;
    }

    public String removePlayer(Player u,Tournament t){
        String output = "";
        if(t != null) {
            if (t.searchMember(u.getName()) != null) {
                output = "  Player " + u.getMail() + " removed from Tournament " + t.getName();
                t.deletePlayer((Player)t.searchMember(u.getName()));
            } else {
                output = "  The player " + u.getMail() + " is not logged in the selected tournament";
            }

        }else{
            output = "  The tournament  does not exists";
        }
        return output;
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
                output.append("The tournament "+tournament.getName()+" was finished the day "+tournament.getEndDate().toString()+"and is being deleted");
                delete(tournament.getName());
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
            output = "Emparejamientos realizados con exito";
            if (tournament.getMembers().size() % 2 != 0) {
                output = ("No se pueden emparejar todos los jugadores (numero de jugadores debe ser par)\n");
            } else {
                List<Member> playerCopy = tournament.getMembers();
                Collections.shuffle(playerCopy);
                for (int i = 0; i < tournament.getMembers().size(); i += 2) {
                    Matchmaking m = new Matchmaking(playerCopy.get(i), playerCopy.get(i+1));
                    tournament.addMatchups(m);
                }
            }
            return output;
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

    private String matchmakeAux(String[] args,Tournament tournament){
        tournament.clearMathchups();
        for(int i = 0;i<args.length;i+=2){
            Member m = tournament.searchMember(args[i]);
            Member m2 = tournament.searchMember(args[i+1]);
            if(m != null && m2 != null){
                Matchmaking match = new Matchmaking(m,m2);
                tournament.addMatchups(match);
            }else{
                return "One of the members is not in the tournament";
            }
        }
        return showMatchmakes(tournament);
    }

    private String rankAux(Tournament tournament){
        StringBuilder output = new StringBuilder();
        ArrayList<Member> sortedPlayerList = tournament.getMembers();
        sortedPlayerList.sort((Member p1, Member p2) -> p2.getCategory(tournament.getCategoria()).compareTo(p1.getCategory(tournament.getCategoria())));
        for (Member team : sortedPlayerList) {
            output.append(team.getName() +" "+team.getCategory(tournament.getCategoria())+ "\n");
        }
        return output.toString();
    }

    private String showMatchmakes(Tournament tournament){
        StringBuilder builder = new StringBuilder();
        for(Matchmaking m : tournament.getMatches()){
            builder.append(m.toString()).append("\n");
        }
        return builder.toString();
    }
}
