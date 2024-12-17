package upm.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;


public class Admin extends User{

    private ArrayList<User> users;

    public Admin(String mail, String password) {
        super(password, mail, Users.ADMIN);
    }

    public Admin() {}

    public ArrayList<User> getUsers() {
        return users;
    }
    public void addUser(User user) {
        users.add(user);
    }
}
