package upm.controller;


import upm.model.Admin;
import upm.model.Player;
import upm.model.User;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Contiene una lista de users para comprobar el user
 * Los booleanos isAdmin y isPlayer se cambian para saber en todos los momentos que tipo de usuario esta registrado
 * Logged es el usuario que esta registrado
 */
public class PublicController {
    private ArrayList<User> users;
    private boolean isAdmin;
    private boolean isPlayer;
    private User logged;

    public PublicController() {
        users = new ArrayList<>();
        isAdmin = false;
        isPlayer = false;
        logged = null;
    }

    /**
     * @return si el usuario registrado es un admin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * @return si el usuario registrado es un player
     */
    public boolean isPlayer() {
        return isPlayer;
    }

    public User getLogged() {
        return logged;
    }

    /**
     * Logs a user
     * @param params  An array of strings containing the user's email and password.
     * @return A string indicating the result of the login attempt.
     *
     */
    public String login(String[] params) {
        String output;
        User user = searchUser(params[0]);
        if(user != null) {
            if(user.getPassword().equals(params[1])) {
               if(user.getUser() == User.Users.PLAYER){
                  isPlayer = true;
                  logged = user;
               }else{
                   isAdmin = true;
                   logged = user;
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

    /**
     * Logsout the user, only if it is a user
     * @return A string indicating the result of the logout attempt
     */
    public String logout() {
        String output = "";
        if(isAdmin || isPlayer){
            isAdmin = false;
            isPlayer = false;
            output = "Logged out";
        }else{
            output = "The user has not been logged in";
        }
        return output;
    }

    /**
     * Adds a user to the users list
     * @param user User to add to the list
     */
    public void signUpUser(User user) {
        users.add(user);
    }

    /**
     * @param mail username of the player to be searched
     * @return object User that is being searched
     */
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

    public Player getPlayer(User user) {
        if(isPlayer){
            return (Player) user;
        }else{
            return null;
        }
    }

    public Admin getAdmin(User user) {
        if(isAdmin){
            return (Admin) user;
        }else{
            return null;
        }
    }
}
