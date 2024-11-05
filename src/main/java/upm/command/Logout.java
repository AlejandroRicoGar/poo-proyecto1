package upm.command;

import upm.controller.PublicController;

public class Logout implements Command{
    private PublicController controller;

    public Logout(PublicController controller) {
        this.controller = controller;
    }


    @Override
    public String apply(String[] stringsep) {
        return "";
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public String getCommand() {
        return "";
    }
}
