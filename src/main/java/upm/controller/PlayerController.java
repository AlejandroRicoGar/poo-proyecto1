package upm.controller;

import upm.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {
    private boolean logged;

    public PlayerController(boolean logged) {
        this.logged = logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged(){
        return logged;
    }


}
