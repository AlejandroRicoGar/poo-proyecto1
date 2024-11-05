package upm.model;

public class Admin extends User{
    public Admin(String mail, String password) {
        super(password, mail, Users.ADMIN);
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
