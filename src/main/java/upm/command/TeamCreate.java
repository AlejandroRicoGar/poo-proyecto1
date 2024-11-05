package upm.command;

import upm.controller.TeamController;

public class TeamCreate implements Command{
    private TeamController admin;
    public TeamCreate(TeamController admin) {
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
