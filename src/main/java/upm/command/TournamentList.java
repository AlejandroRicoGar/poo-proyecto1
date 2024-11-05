package upm.command;

import upm.controller.TournamentController;

public class TournamentList implements Command{
    private TournamentController controller;
    public TournamentList(TournamentController controller) {
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
