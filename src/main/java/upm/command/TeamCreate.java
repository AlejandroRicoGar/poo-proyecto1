package upm.command;

import upm.controller.TeamController;
import upm.model.Team;

public class TeamCreate implements Command{
    private TeamController teamController;
    public TeamCreate(TeamController controller) {
        this.teamController = controller;
    }
    @Override
    public String apply(String[] stringsep) {
            String output = "";
            if(stringsep.length == 1) {
                if(teamController.searchTeam(stringsep[0])==null)
                    output=teamController.addTeam(new Team(stringsep[0]));
                else
                    output="Team already exists";
            }
            else{
                output = "Incorrect number of parameters, expected only team name";
            }
            return output;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public String getCommand() {
        return "team-create";
    }
}
