package upm.model;
public class Player {
    private String name;
    private Double score;

    /**
     * Crea un nuevo objeto Player con el nombre dado e inicializa la puntuacion por defecto a 0.0
     *
     * @param name el nombre del jugador
     */
    public Player(String name){
        this.name = name;
        this.score = 0.0;
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


    public Double getScore() {
        return score;
    }


    public void setScore(Double score) {
        this.score = score;
    }


    @Override
    public String toString(){
        return name+" ("+score+")";
    }


}