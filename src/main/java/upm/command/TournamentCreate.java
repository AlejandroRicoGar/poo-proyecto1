package upm.command;

import upm.controller.AdminController;
import upm.model.Admin;
import upm.model.Tournament;

public class TournamentCreate implements Command{
    private AdminController admin;
    public TournamentCreate(AdminController admin) {
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
