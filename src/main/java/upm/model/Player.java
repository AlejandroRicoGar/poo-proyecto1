package upm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Player extends User implements Member{

    @Column
    private String name;
    @Column
    private String surname;

    @Id
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "p")
    private Set<Category> categories;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "player_tournaments",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_name")
    )
    private Set<Tournament> tournaments;

    @ManyToOne()
    @JoinColumn(name = "admin_id")
    private Admin creator;

    @ManyToMany(mappedBy = "members")
    private Set<Team> teams;

    private static long idGen = 0L;


    /**
     *
     */
    public Player(String name, String surname, String password, String email,Admin creator) {
        super(password,email,Users.PLAYER);
        this.name = name;
        this.surname = surname;
        this.id = idGen++;
        this.creator = creator;

        Category points = new Category(Categories.SCORED_POINTS,0.0);
        Category matchWon = new Category(Categories.MATCH_WON,0.0);
        Category assistsPoints = new Category(Categories.ASISTS_POINTS,0.0);
        Category pastTournaments = new Category(Categories.PAST_TOURNAMENTS,0.0);
        Category generatedMoney = new Category(Categories.GENERATED_MONEY,0.0);

        categories = new HashSet<>();

        categories.add(points);
        categories.add(matchWon);
        categories.add(assistsPoints);
        categories.add(pastTournaments);
        categories.add(generatedMoney);

        tournaments = new HashSet<>();
    }

    public Player() {}
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getCategoryValue(Categories C){
        Double resul = 0.0;
        for(Category c : categories){
            if(c.getType().equals(C)){
                resul =  c.getValue();
            }
        }

        return resul;
    }
    public void setCategoryValue(Categories C, Double value){
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

    public Set<Tournament> getTournaments() {
        return tournaments;
    }

    @Override
    public boolean contains(Player p) {
        return id.equals(p.getId());
    }

    public Long getId() {
        return id;
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