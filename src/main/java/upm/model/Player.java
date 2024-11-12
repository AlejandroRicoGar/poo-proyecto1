package upm.model;

import java.util.ArrayList;
import java.util.List;

public class Player extends User implements Member{
    private String name;
    private String surname;
    private String id;
    private ArrayList<Category> categories;
    private ArrayList<Tournament> tournaments;
    private Admin creator;


    /**
     *
     */
    public Player(String name, String surname, String id, String password, String email,Admin creator) {
        super(password,email,Users.PLAYER);
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.creator = creator;

        Category points = new Category(Categories.SCORED_POINTS,0.0);
        Category matchWon = new Category(Categories.MATCH_WON,0.0);
        Category assistsPoints = new Category(Categories.ASISTS_POINTS,0.0);
        Category pastTournaments = new Category(Categories.PAST_TOURNAMENTS,0.0);
        Category generatedMoney = new Category(Categories.GENERATED_MONEY,0.0);

        categories = new ArrayList<Category>();

        categories.add(points);
        categories.add(matchWon);
        categories.add(assistsPoints);
        categories.add(pastTournaments);
        categories.add(generatedMoney);

        tournaments = new ArrayList<Tournament>();
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getCategory(Categories C){
        Double resul = 0.0;
        for(Category c : categories){
            if(c.getType().equals(C)){
                resul =  c.getValue();
            }
        }

        return resul;
    }
    public void setCategory(Categories C, Double value){
        for(Category c : categories){
            if(c.getType().equals(C)){
                c.setValue(value);
            }

        }
    }

    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
    }

    @Override
    public void deleteTournament(Tournament tournament) {
        tournaments.remove(tournament);
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ID='" + id + '\'' +
                '}';
    }
}