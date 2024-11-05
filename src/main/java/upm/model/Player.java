package upm.model;

import upm.view.Users;

public class Player extends User{
    private String name;
    private String surname;
    private String ID;


    /**
     * Crea un nuevo objeto Player con el nombre dado e inicializa la puntuacion por defecto a 0.0
     *
     * @param name el nombre del jugador
     */
    public Player(String name, String surname, String ID, String password, String email,Users users) {
        super(password,email,users);
        this.name = name;
        this.surname = surname;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}