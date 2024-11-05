package upm.command;

import upm.controller.TournamentController;

public class TournamentAdd implements Command{
    private TournamentController playerController;
    public TournamentAdd(TournamentController playerController) {
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
