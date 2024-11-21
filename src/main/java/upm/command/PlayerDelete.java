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
     * Deletes a player if he is not in a team with active tournaments or minimal players and he is not in a tournament
     * @param stringsep an array of strings with the parameters "player-delete playerId"
     * @return the output of the command
     */
    @Override
public String apply(String[] stringsep) {
    if (stringsep.length != 2) {
        return "Incorrect number of parameters";
    }

    String playerId = stringsep[1];
    Player player = PlayerController.getInstance().search(playerId);

    if (player == null) {
        return "The player with id " + playerId + " does not exist";
    }

    if (!player.getTournaments().isEmpty()) {
        return "The player " + player.getName() + " is in a tournament";
    }

    if (!canRemovePlayerFromTeams(player)) {
        return "The player " + player.getName() + " is in a team with active tournaments";
    }

    if (!TeamController.getInstance().deletePlayerFromAllTeams(player)) {
        return "The player " + player.getName() + " is in a minimum sized team (2 players)";
    }

    return PlayerController.getInstance().deletePlayer(player);
}

private boolean canRemovePlayerFromTeams(Player player) {
    for (Team team : TeamController.getInstance().getTeams()) {
        if (Boolean.TRUE.equals(team.isMember(player)) && !team.getTournaments().isEmpty()) {
            return false;
        }
    }
    return true;
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


