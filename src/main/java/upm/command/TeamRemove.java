package upm.command;

import upm.controller.TeamController;
import upm.model.Team;

public class TeamRemove implements Command{
    private TeamController teamController;
    public TeamRemove(TeamController tcontroller) {
        this.teamController = tcontroller;
    }
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        if(stringsep.length == 2) {
            String[] params = stringsep[1].split(";");
            Team team=teamController.searchTeam(params[0]);
            if(team!=null)
                if(team.getMember(params[1])!=null){
                    output= teamController.deletePlayerFromTeam(team.getMember(params[1]),team);
                }else
                    output="Team does not have the player";
            else
                output="Team doesnt exist";
        }
        else{
            output = "Incorrect number of parameters, expected team name and playerId";
        }
        return output;
    }

    @Override
    public String toString() {
        return "team-remove teamname;playerId";
    }

    @Override
    public String getCommand() {
        return "team-remove";
    }
}
