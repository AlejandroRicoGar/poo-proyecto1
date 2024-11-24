package upm.command;

import upm.controller.PublicController;

public class Logout implements Command{


    /**
     * Logs out the user
     *
     * @param params Command parameters
     * @return The result of the logout attempt
     */
    @Override
    public String apply(String[] params) {
        return PublicController.getInstance().logout();
    }

    /**
     * @return Command that is going to be shown in the screen
     */
    @Override
    public String toString() {
        return "> logout";
    }

    /**
     * @return The String of the command that is going to be commpared with the input
     */
    @Override
    public String getCommand() {
        return "logout";
    }
}
