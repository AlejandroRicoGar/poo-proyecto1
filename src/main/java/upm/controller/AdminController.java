package upm.controller;


import upm.model.*;


import java.util.ArrayList;

public class AdminController {
    private ArrayList<Player> players;
    private boolean loggedin = false;

    public void setLoggedin(boolean loggedin) {
        this.players = new ArrayList<>();
        this.loggedin = loggedin;
    }

    public AdminController(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public boolean isLoggedin() {
        return loggedin;
    }
}
