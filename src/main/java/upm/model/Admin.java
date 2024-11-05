package upm.model;

import upm.view.Users;

public class Admin extends User{
    public Admin(String mail, String password, Users user) {
        super(password, mail, user);
    }
}
