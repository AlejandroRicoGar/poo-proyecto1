package upm.model;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class Player extends User implements Member{
    private String name;
    private String surname;
    private String ID;
    private ArrayList<Category> categories;
    private ArrayList<Tournament> tournaments;
    private Admin creator;


    /**
     *
     */
    public Player(String name, String surname, String ID, String password, String email,Admin creator) {
        super(password,email,Users.PLAYER);
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.creator = creator;

        Category points = new Category(Categorys.SCORED_POINTS,0.0);
        Category matchWon = new Category(Categorys.MATCH_WON,0.0);
        Category AsistsPoints = new Category(Categorys.ASISTS_POINTS,0.0);
        Category pastTournaments = new Category(Categorys.PAST_TOURNAMENTS,0.0);
        Category generatedMoney = new Category(Categorys.GENERATED_MONEY,0.0);

        categories = new ArrayList<Category>();

        categories.add(points);
        categories.add(matchWon);
        categories.add(AsistsPoints);
        categories.add(pastTournaments);
        categories.add(generatedMoney);

        tournaments = new ArrayList<Tournament>();
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getCategory(Categorys C){
        Double resul = 0.0;
        for(Category c : categories){
            if(c.getType().equals(C)){
                resul =  c.getValue();
            }
        }

        return resul;
    }
    public void setCategory(Categorys C, Double value){
        for(Category c : categories){
            if(c.getType().equals(C)){
                c.setValue(value);
            }

        }
    }

    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
    }

    public void removeTournament(Tournament tournament) {
        tournaments.remove(tournament);
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