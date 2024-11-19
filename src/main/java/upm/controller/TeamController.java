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
    
    public String addPlayerToTeam(Player player, Team team) {//Porque
        String output = "";
        if (team != null) {
            team.addMember(player);
            output = "Player with Id " + player.getId() + " added to team " + team.getName();
        } else {
            output = "The team does not exist";
        }
        return output;
    }

    public List<Team> getTeams() {
        return teams;
    }
    public void deleteTeam(Team team){
        teams.remove(team);
    }
    public boolean deletePlayerFromTeam(Player player, Team team) {
        if (team.getTeamSize()>2) {
            //"Player with Id " + player.getId() + " deleted from team " + team.getName();
            team.deleteMember(player);
            return true;
        } else {
            //"Cannot delete player because minimum team size 2";
            return false;
        }
    }
}
