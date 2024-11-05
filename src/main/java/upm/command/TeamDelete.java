package upm.command;

import upm.controller.TeamController;

public class TeamDelete implements Command{
    private TeamController admin;

    public TeamDelete(TeamController admin) {
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
