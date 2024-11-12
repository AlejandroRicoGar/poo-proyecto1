package upm.controller;

import upm.model.Team;
import upm.model.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamController {
    private ArrayList<Team> teams;

    public TeamController() {
        teams = new ArrayList<>();
    }

    public void addTeam(Team team){
        teams.add(team);
    }

    public Team searchTeam(String name){
        Team team = null;
        if(!teams.isEmpty()){
            for(Team t : teams){
                if(t.getName().equals(name)){
                    team = t;
                }
            }
        }
        return team;
    }
    
    public String addPlayerToTeam(Player player, Team team) {
        String output = "";
        if (team != null) {
            team.addMember(player);
            output = "Player with email " + player.getName() + " added to team " + team.getName();
        } else {
            output = "The team does not exist";
        }
        return output;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
