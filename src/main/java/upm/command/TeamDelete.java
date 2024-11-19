package upm.command;

import upm.controller.TeamController;
import upm.model.Team;

public class TeamDelete implements Command{
    private TeamController teamController;

    public TeamDelete(TeamController tcontroller) {
        this.teamController = tcontroller;
    }
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        if(stringsep.length == 2) {
            Team team=teamController.searchTeam(stringsep[1]);
            if(team!=null)
                if(team.getTournaments().isEmpty())
                    output=teamController.deleteTeam(team);
                else
                    output="Team cannot be deleted if it is in a tournament";
            else
                output="Team doesnt exist";
        }
        else{
            output = "Incorrect number of parameters, expected only team name";
        }
        return output;
    }

    @Override
    public String toString() {
        return "team-delete name";
    }

    @Override
    public String getCommand() {
        return "team-delete";
    }
}
