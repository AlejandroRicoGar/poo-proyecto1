package upm;

public class Match {
    private Player player1;
    private Player player2;

    /**
     * Constructor de la clase
     *
     * @param player1 Objeto Player
     * @param player2 Objeto Player
     */
    public Match (Player player1, Player player2){
        this.player1=player1;
        this.player2=player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    @Override
    public String toString() {
        return "Emparejamiento entre "+player1.getName()+" y "+player2.getName();
    }
}
