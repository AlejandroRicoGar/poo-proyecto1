package upm.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public abstract class User {
    @Id
    @Column(name = "mail")
    private String mail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "type", nullable = false)
    private Users user;

    public User(String password, String mail, Users user) {
        this.password = password;
        this.mail = mail;
        this.user = user;
    }

    public User() {}

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

    public enum Users {
        ADMIN,
        PLAYER,
        PUBLIC
    }


}
