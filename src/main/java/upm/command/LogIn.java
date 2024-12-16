package upm.command;

import upm.controller.PublicController;

public class LogIn implements Command{


    /**
     * Logs a user in
     * @param params  An array of strings containing the user's email and password.
     * @return A string indicating the result of the login attempt.
     */
    @Override
    public String apply(String[] params) {
        String output = "";
        try {
            if (!(PublicController.getInstance().isAdmin() || PublicController.getInstance().isPlayer())) {
                if (params.length == 2) {
                    String[] arguments = params[1].split(";");

                    if (arguments.length == 2) {

                        output = PublicController.getInstance().login(arguments);
                    } else {
                        output = "Incorrect number of arguments.";
                    }
                } else {
                    output = "Incorrect number of arguments";
                }
            } else {
                output = "You are already logged in, if you wish to use another account logout and login again";
            }
        } catch (NullPointerException e) {
            output = "login error";
        }

        return output;
    }

    @Override
    public String toString() {
        return "> login email;password ";
    }

    @Override
    public String getCommand() {
        return "login";
    }
}

