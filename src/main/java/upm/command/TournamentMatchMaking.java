package upm.command;

import upm.controller.TournamentController;

public class TournamentMatchMaking implements Command {
    private TournamentController admin;
    public TournamentMatchMaking(TournamentController admin) {
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
