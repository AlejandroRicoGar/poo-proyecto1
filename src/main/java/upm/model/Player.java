package upm.model;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class Player extends User{
    private String name;
    private String surname;
    private String ID;
    private ArrayList<Category> categories;
    private ArrayList<Tournament> tournaments;


    /**
     * Crea un nuevo objeto Player con el nombre dado e inicializa la puntuacion por defecto a 0.0
     *
     * @param name el nombre del jugador
     */
    public Player(String name, String surname, String ID, String password, String email) {
        super(password,email,Users.PLAYER);
        this.name = name;
        this.surname = surname;
        this.ID = ID;

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

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
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