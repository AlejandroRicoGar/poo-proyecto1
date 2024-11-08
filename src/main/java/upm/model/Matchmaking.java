package upm.model;

public class Matchmaking {
    private Member member1;
    private Member member2;

    @Override
    public String toString() {
        return "Matchmaking{" +
                "member1=" + member1 +
                ", member2=" + member2 +
                '}';
    }

    /**
     * Constructor de la clase
     *
     * @param player1 Player 1
     * @param player2 Player 2
     */
    public Matchmaking(Member player1, Member player2){
        this.member1 = player1;
        this.member2 = player2;
    }
    public Member getPlayer1() {
       return member1;
    }

    public Member getPlayer2() {
        return member2;
    }



}
