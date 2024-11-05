package upm.command;

import upm.controller.PlayerController;
import upm.model.Player;

public class TournamentRemove extends Command{
    private PlayerController pc;
    public TournamentRemove(PlayerController pc) {
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
