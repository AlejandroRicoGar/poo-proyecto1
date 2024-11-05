package upm.command;

import upm.controller.AdminController;

public class TeamCreate extends Command{
    private AdminController admin;
    public TeamCreate(AdminController admin) {
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
