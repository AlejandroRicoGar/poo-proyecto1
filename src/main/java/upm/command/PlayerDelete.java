package upm.command;

import upm.controller.PlayerController;
import upm.controller.TeamController;
import upm.model.Team;

public class PlayerDelete implements Command {
    private PlayerController playerController;
    private TeamController teamController;

    public PlayerDelete(PlayerController playerController, TeamController teamController) {
        this.playerController = playerController;
        this.teamController = teamController;
    }

    @Override
    public String apply(String[] stringsep) {
        String output = "";
        Boolean isInATeam = false;
        if(stringsep.length == 1) {
            for (Team team : teamController.getTeams()) {
                if (Boolean.TRUE.equals(team.isMember(playerController.search(stringsep[0]))) && !team.getTournaments().isEmpty()) {
                        output = "The player " + stringsep[0] + " is in a team with active tournaments, you must remove the player from the team or remove the team from the tournament before deleting it";
                        isInATeam = true;
                        break;
                    }
            }
            if (Boolean.FALSE.equals(isInATeam)) {
                output = playerController.deletePlayer(stringsep[0]);
            }
        } else {
            output = "Incorrect number of parameters, only the name of the player is required";
        }
        return output;
    }

    @Override
    public String toString() {
        return "> player-delete playerName";
    }

    @Override
    public String getCommand() {
        return "player-delete";
    }
}
