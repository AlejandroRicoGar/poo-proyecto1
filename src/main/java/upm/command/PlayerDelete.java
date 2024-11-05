package upm.command;

import upm.controller.AdminController;
import upm.model.Player;

public class PlayerDelete implements Command{
    private AdminController admin;

    public PlayerDelete(AdminController admin) {
        this.admin = admin;
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
