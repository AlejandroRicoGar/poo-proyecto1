package upm.command;

import upm.controller.PlayerController;

public class TournamentAdd implements Command{
    private PlayerController playerController;
    public TournamentAdd(PlayerController playerController) {
        this.playerController = playerController;
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
