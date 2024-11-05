package upm.command;

import upm.controller.PlayerController;
import upm.controller.PublicController;

public class StaticsShow implements Command{
    private PlayerController controller;
    public StaticsShow(PlayerController controller) {
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
