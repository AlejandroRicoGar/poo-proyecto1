package upm.command;

import upm.controller.PlayerController;
import upm.controller.TeamController;

public class TeamAdd implements Command {

    private TeamController teamController;
    private PlayerController playerController;

    public TeamAdd(TeamController teamController, PlayerController playerController) {
        this.teamController = teamController;
        this.playerController = playerController;
    }
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        String[] params = stringsep[1].split(";");
        if(params.length == 2) {
            if (playerController.search(params[0]) != null) {
                output = teamController.addPlayerToTeam(playerController.search(params[0]), teamController.search(params[1]));
            } else {
                output = "The player does not exist";
            }
        } else {
            output = "Incorrect number of parameters";
        }
        return output;
    }

    @Override
    public String toString() {
        return "> team-add PlayerName;TeamName";
    }

    @Override
    public String getCommand() {
        return "team-add";
    }
}
