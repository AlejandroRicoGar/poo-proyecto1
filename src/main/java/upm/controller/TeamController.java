package upm.controller;

import upm.model.Team;

import java.util.ArrayList;

public class TeamController {
    private ArrayList<Team> teams;

    public TeamController() {
        teams = new ArrayList<>();
    }

    public Team search(String name){
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
    public void addTeam(Team team){
        teams.add(team);
    }
}
