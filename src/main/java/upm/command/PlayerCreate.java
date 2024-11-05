package upm.command;

import upm.controller.AdminController;
import upm.controller.PlayerController;


public class PlayerCreate implements Command{
    private AdminController controller;

    public PlayerCreate(AdminController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] stringsep) {
        return "";
    }

    @Override
    public String toString() {
        return "> player-delete";
    }

    @Override
    public String getCommand() {
        return "";
    }
}
