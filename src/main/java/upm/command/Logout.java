package upm.command;

import upm.controller.PublicController;

public class Logout implements Command{
    private PublicController controller;

    public Logout(PublicController controller) {
        this.controller = controller;
    }


    @Override
    public String apply(String[] params) {
        String output = "";
        output = controller.logout();
        return output;
    }

    @Override
    public String toString() {
        return "> logout";
    }

    @Override
    public String getCommand() {
        return "logout";
    }
}
