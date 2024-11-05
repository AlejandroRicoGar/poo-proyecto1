package upm.command;

import upm.controller.PlayerController;

public class PlayerDelete implements Command{
    private PlayerController admin;

    public PlayerDelete(PlayerController admin) {
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
