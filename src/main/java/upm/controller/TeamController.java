package upm.controller;

import upm.model.Team;

import java.util.ArrayList;

public class TeamController {
    private ArrayList<Team> teams;

    public TeamController() {
        teams = new ArrayList<>();
    }

    public Team search(String name){
        Team Team = null;
        if(!teams.isEmpty()){
            for(Team t : teams){
                if(t.getName().equals(name)){
                    Team = t;
                }
            }
        }
        return Team;
    }
    public void addTeam(Team team){
        teams.add(team);
    }
}
