package upm.model;

public class Matchmaking {
    private Player player1;
    private Player player2;
    private Team team1;
    private Team team2;
    /**
     * Constructor de la clase
     *
     * @param player1 Player 1
     * @param player2 Player 2
     */
    public Matchmaking(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }
    public Matchmaking(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
    }

    public Player getPlayer1() {
       return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    @Override
    public String toString() {
        String output = "";

        if(team1 != null){
            output = "Emparejamiento entre "+team1.getName()+" y "+team2.getName();
        }else{
            output = "Emparejamiento entre "+player1.getName()+" y "+player2.getName();
        }
        return output;

    }
}
