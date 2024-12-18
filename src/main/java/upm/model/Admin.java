package upm.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Admin extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
