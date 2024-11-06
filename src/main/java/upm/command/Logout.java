package upm.command;

import upm.controller.PublicController;

public class Logout implements Command{
    private PublicController controller;

    public Logout(PublicController controller) {
        this.controller = controller;
    }


    /**
     * @param params Division por espacios el param[0] es el comando en si y el param[1] son los argumentos
     * @return String indicating the result of the logout attempt
     */
    @Override
    public String apply(String[] params) {
        String output = "";
        output = controller.logout();
        return output;
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
