package upm.command;

import upm.controller.TeamController;
import upm.model.Player;
import upm.model.Team;

public class TeamRemove implements Command{
    @Override
    public String apply(String[] stringSep) {
        if (stringSep.length != 2) {
            return "Incorrect number of parameters, expected team name and playerId";
        }

        String[] params = stringSep[1].split(";");
        if (params.length != 2) {
            return "Incorrect number of parameters, expected team name and playerId";
        }

        String teamName = params[0];
        String playerId = params[1];

        Team team = TeamController.getInstance().searchTeam(teamName);
        if (team == null) {
            return "Team doesn't exist";
        }

        Player player = team.getMember(playerId);
        if (player == null) {
            return "Team does not have the player";
        }

        if (!TeamController.getInstance().deletePlayerFromTeam(player, team)) {
            return "Cannot delete player because minimum team size 2";
        }

        return "Player with Id " + player.getId() + " deleted from team " + team.getName();
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
