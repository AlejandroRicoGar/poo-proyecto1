package upm.command;


import upm.controller.PlayerController;

public class PlayerCreate implements Command{
    private PlayerController controller;

    public PlayerCreate(PlayerController controller) {
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
