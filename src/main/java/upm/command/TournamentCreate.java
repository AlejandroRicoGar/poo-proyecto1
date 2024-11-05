package upm.command;

import upm.controller.TournamentController;

public class TournamentCreate implements Command{
    private TournamentController admin;
    public TournamentCreate(TournamentController admin) {
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
