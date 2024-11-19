/**
 * Command to delete a player from the system
 */
package upm.command;

import upm.controller.PlayerController;
import upm.controller.TeamController;
import upm.model.Player;
import upm.model.Team;

/**
 * Class to delete a player from the system
 */
public class PlayerDelete implements Command {

    /**
     * The player controller
     */
    private PlayerController playerController;

    /**
     * The team controller
     */
    private TeamController teamController;

    /**
     * Constructor of the class
     * @param playerController the player controller
     * @param teamController the team controller
     */
    public PlayerDelete(PlayerController playerController, TeamController teamController) {
        this.playerController = playerController;
        this.teamController = teamController;
    }

    /**
     * Deletes a player if he is not in a team with active tournaments or minimal players and he is not in a tournament
     * @param stringsep an array of strings with the parameters "player-delete playerId"
     * @return the output of the command
     */
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        if (stringsep.length != 2) {
            output = "Incorrect number of parameters";
        } else {
            String[] params = stringsep[1].split(";");
                String playerId = params[0];
                Player player = playerController.search(playerId);
                if (player != null) {
                    if (player.getTournaments().isEmpty()) {
                        boolean canRemove = true;
                        for (Team team : teamController.getTeams()) {
                            if (Boolean.TRUE.equals(team.isMember(player)) && !team.getTournaments().isEmpty()) {
                                output = "The player " + player.getName() + " is in a team with active tournaments, you must remove the player from the team or remove the team from the tournament before deleting it";
                                canRemove = false;
                                break;
                            }
                        }
                        if (canRemove) {
                            if (teamController.deletePlayerFromAllTeams(player)) {
                                output = playerController.deletePlayer(player);
                            } else {
                                output = "The player " + player.getName() + " is in a minimum sized team (2 players)";
                            }
                        }
                    } else {
                        output = "The player " + player.getName() + " is in a tournament, you must remove the player from the tournament before deleting it";
                    }
                } else {
                    output = "The player with id " + playerId + "does not exist";
                }
        }
        return output;
    }

    /**
     * Returns a string with the description of the command
     * @return the description of the command
     */
    @Override
    public String toString() {
        return "> player-delete playerId";
    }

    /**
     * Returns the name of the command
     * @return the name of the command
     */
    @Override
    public String getCommand() {
        return "player-delete";
    }
}


