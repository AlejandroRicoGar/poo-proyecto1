package upm.controller;


import upm.model.User;

import java.util.ArrayList;
import java.util.Iterator;

public class PublicController {
    private ArrayList<User> users;
    private PlayerController playerController;
    private AdminController adminController;

    public PublicController(PlayerController playerController, AdminController adminController) {
        users = new ArrayList<>();
        this.adminController = adminController;
        this.playerController = playerController;
    }

    public String login(String[] params) {
        String output;
        User user = searchUser(params[0]);
        if(user != null) {
            if(user.getPassword().equals(params[1])) {
               if(user.getUser() == User.Users.PLAYER){
                  playerController.setLogged(true);
               }else{
                   adminController.setLoggedin(true);
               }
               output = "User "+user.getMail()+" Logged in";
            }else{
                output = "Login error";
            }
        }else{
            output = "Login error";
        }
        return output;
    }

    public void signUpUser(User user) {
        users.add(user);
    }
    private User searchUser(String mail) {
        Iterator<User> iter = users.iterator();
        boolean exists = false;
        User user = null;
        while (!exists && iter.hasNext()) {
            User aux = iter.next();
            if (aux.getMail().equals(mail)) {
                user = aux;
                exists = true;
            }
        }
        return user;
    }

}
