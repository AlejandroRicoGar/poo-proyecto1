package upm.command;

import upm.controller.PublicController;

public class TournamentList extends Command{
    private PublicController controller;
    public TournamentList(PublicController controller) {
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
