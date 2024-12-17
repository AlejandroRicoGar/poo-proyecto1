package upm.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

public class Admin extends User{
    @ManyToOne
    @JoinColumn(name = "creator")
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
