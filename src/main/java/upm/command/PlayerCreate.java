/**
 * Command to create a player
 */
package upm.command;

import upm.controller.PlayerController;
import upm.controller.PublicController;

/**
 * Class to create a player
 */
public class PlayerCreate implements Command {
    /**
     * Player controller to create the player
     */
    private PlayerController playerController;
    /**
     * Public controller to get the admin that is logged
     */


    /**
     * Constructor of the class
     * @param playerController Player controller to create the player
     */
    public PlayerCreate(PlayerController playerController) {
        this.playerController = playerController;

    }

    /**
     * Creates a player
     * @param stringsep An array with the parameters "player-create name;surname;id;password;email"
     * @return The output of the command
     */
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        String[] params = stringsep[1].split(";");
        if(params.length == 5) {
            output = playerController.createPlayer(params[0], params[1], params[2], params[3], params[4], PublicController.getInstance().getAdmin(PublicController.getInstance().getLogged()));
            if (playerController.search(params[2]) != null) {
                PublicController.getInstance().signUpUser(playerController.search(params[2]));
            }
        } else {
            output = "Incorrect number of parameters";
        }
        return output;
    }

    /**
     * Returns a string with the command description
     * @return The command description
     */
    @Override
    public String toString() {
        return "> player-create name;surname;id;password;email";
    }

    /**
     * Returns the command name
     * @return The command name
     */
    @Override
    public String getCommand() {
        return "player-create";
    }
}

