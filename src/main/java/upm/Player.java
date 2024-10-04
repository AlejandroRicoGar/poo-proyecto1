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

    /**
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name that is going to be changed
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the score of the player
     */
    public Double getScore() {
        return score;
    }

    /**
     * @param score the score that is going to be changed
     */
    public void setScore(Double score) {
        this.score = score;
    }


    /**
     * @return a way to recognise the player
     */
    public String toString(){
        return name+" ("+score+")";
    }


}