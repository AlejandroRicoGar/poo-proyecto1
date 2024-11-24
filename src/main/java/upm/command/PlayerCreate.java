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
     * Creates a player
     * @param arguments An array with the parameters "player-create name;surname;id;password;email"
     * @return The output of the command
     */
    @Override
    public String apply(String[] arguments) {
        String result = "";
        if (arguments.length != 2) {
            result = "Incorrect number of parameters";
        } else {
            String[] params = arguments[1].split(";");
            if (params.length == 5) {
                String name = params[0];
                String surname = params[1];
                String id = params[2];
                String password = params[3];
                String email = params[4];

                result = PlayerController.getInstance().createPlayer(
                    name, surname, id, password, email, 
                    PublicController.getInstance().getAdmin(PublicController.getInstance().getLogged())
                );
            } else {
                result = "Incorrect number of parameters";
            }
        }
        return result;
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

