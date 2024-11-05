package upm.model;

import upm.view.Users;

public abstract class User {
    private String mail;
    private String password;
    private Users user;

    public User(String password, String mail, Users user) {
        this.password = password;
        this.mail = mail;
        this.user = user;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public Users getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}