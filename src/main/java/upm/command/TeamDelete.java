package upm.command;

import upm.controller.AdminController;
import upm.model.Admin;

public class TeamDelete implements Command{
    private AdminController admin;

    public TeamDelete(AdminController admin) {
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
