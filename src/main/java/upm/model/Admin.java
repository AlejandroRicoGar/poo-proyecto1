package upm.model;

public class Admin extends User{
    public Admin(String mail, String password) {
        super(password, mail, Users.ADMIN);
    }
}
