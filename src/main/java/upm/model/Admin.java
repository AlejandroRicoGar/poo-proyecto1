package upm.model;

import upm.view.Users;

public class Admin extends User{
    public Admin(String mail, String password, Users user) {
        super(password, mail, user);
    }

    public void register(Player p){

    }
    public void register(Tournament t){

    }
    public void register(Team t){

    }
    public String matchmakingMan(Tournament t,String[] players){
        return "";
    }
    public String matchmaking(Team t){
        return "";
    }

}
