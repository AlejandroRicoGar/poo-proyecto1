package upm.model;

import javax.persistence.*;

@Entity
public class Matchmaking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name1")
    private Member member1;

    @Column(name = "name1")
    private Member member2;

    @ManyToOne()
    @JoinColumn(name = "name")
    private Tournament tournament;



    @Override
    public String toString() {
        return "Matchmaking{" +
                "member1=" + member1.getName() +
                ", member2=" + member2.getName() +
                '}';
    }

    /**
     * Constructor de la clase
     *
     * @param player1 Player 1
     * @param player2 Player 2
     */
    public Matchmaking(Member player1, Member player2, Tournament tournament) {
        this.member1 = player1;
        this.member2 = player2;
        this.tournament = tournament;
    }

    public Matchmaking() {
        //Obliga
    }

    public Member getPlayer1() {
       return member1;
    }

    public Member getPlayer2() {
        return member2;
    }



}
