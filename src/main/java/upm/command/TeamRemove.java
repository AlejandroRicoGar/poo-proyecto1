package upm.command;

import upm.controller.TeamController;
import upm.model.Player;
import upm.model.Team;

public class TeamRemove implements Command{
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        if(stringsep.length == 2) {
            String[] params = stringsep[1].split(";");
            Team team=TeamController.getInstance().searchTeam(params[0]);
            if(team!=null) {
                Player player = team.getMember(params[1]);
                if (player != null) {
                    if (TeamController.getInstance().deletePlayerFromTeam(team.getMember(params[1]), team))
                        output = "Player with Id " + player.getId() + " deleted from team " + team.getName();
                    else
                        output = "Cannot delete player because minimum team size 2";
                } else
                    output = "Team does not have the player";
            }
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
        return "> team-remove teamname;playerId";
    }

    @Override
    public String getCommand() {
        return "team-remove";
    }
}
