package upm.command;

import upm.controller.AdminController;

public class TeamAdd extends Command{
    private AdminController admin;
    public TeamAdd(AdminController admin) {
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
