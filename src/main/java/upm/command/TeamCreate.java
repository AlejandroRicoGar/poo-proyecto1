package upm.command;

import upm.controller.PublicController;
import upm.controller.TeamController;
import upm.model.Team;

public class TeamCreate implements Command{
    private TeamController teamController;
    private PublicController publicController;
    public TeamCreate(TeamController tcontroller,PublicController pcontroller) {
        this.teamController = tcontroller;
        this.publicController = pcontroller;
    }
    @Override
    public String apply(String[] stringsep) {
            String output = "";
            if(stringsep.length == 2) {
                if(teamController.searchTeam(stringsep[1])==null)
                    output=teamController.addTeam(new Team(stringsep[1],publicController.getAdmin(publicController.getLogged())));
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
