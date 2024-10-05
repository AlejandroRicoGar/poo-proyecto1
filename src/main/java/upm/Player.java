package upm;
public class Player {
    private String name;
    private Double score;

    /**
     * @param name Name of the player is initialized
     *             Initialices a player with the name and sets its score to 0
     */
    public Player(String name){
        this.name = name;
        this.score = 0.0;
    }

    /**
     * @param name Name of the player is initialized
     * @param score Score of the plauer is initialized
     *              Other option of constructor with score in it
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