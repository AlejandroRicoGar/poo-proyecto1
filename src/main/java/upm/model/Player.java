package upm.model;
public class Player extends User{
    private String name;
    private String surname;
    private String ID;


    /**
     * Crea un nuevo objeto Player con el nombre dado e inicializa la puntuacion por defecto a 0.0
     *
     * @param name el nombre del jugador
     */
    public Player(String name,String surname,String ID) {
        this.name = name;
        this.surname = surname;
        this.ID = ID;
    }

    /**
     * Crea un nuevo objeto Player con el nombre y la puntuacion dada
     *
     * @param name  el nombre del jugador
     * @param score la puntuacion inicial del jugador
     */
    public Player(String name,Double score){
        this.name = name;
        this.score = score;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString(){
        return name+" ("+score+")";
    }


}