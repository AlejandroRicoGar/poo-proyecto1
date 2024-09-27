package upm;
public class Player {
    private String name;
    private Double score;

    public Player(String name){
        this.name = name;
        this.score = 0.0;
    }
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


    public String toString(){
        return name+" "+score;
    }


}