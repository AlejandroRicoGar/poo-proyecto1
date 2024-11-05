package upm.command;

import upm.controller.TournamentController;
import upm.model.Player;

public class TournamentRemove implements Command{
    private TournamentController pc;
    public TournamentRemove(TournamentController pc) {
        this.pc = pc;
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
