package upm;

import java.util.ArrayList;

public class Logic {
    private ArrayList<Match> matchList;
    private ArrayList<Player> playerList;
    private CLI cli;

    /**
     * Metodo que inicializa el programa
     */
    public void start(){
        this.cli = new CLI();
        boolean seguir =true;

        Player Luisa = new Player("Luisa",4.5);
        Player Manuel = new Player("Manuel",2.7);
        Player Kurt = new Player("Luisa",4.5);
        Player Sofia = new Player("Sofia",4.5);
        Player Robert = new Player("Robert",4.5);

        matchList = new ArrayList<>();
        playerList = new ArrayList<>();

        playerList.add(Luisa);
        playerList.add(Manuel);
        playerList.add(Kurt);
        playerList.add(Sofia);
        playerList.add(Robert);
        while(seguir) {
            cli.print("""
                    1> create [player]
                    2> remove [player]
                    3> show
                    4> rank
                    5> score [player];[score]
                    6> show_matchmake
                    7> clear_matchmake
                    8> matchmake [player1];[player2]"
                    9> random_matchmake
                    10> end
                    """);
            int indice = cli.scanner().nextInt();
            switch (indice) {
                case (1):
                    createPlayer();
                    break;
                case (2):
                    cli.print("Introduce el nombre del jugador");
                    removePlayer(cli.scanner().nextLine());
                    break;
                case (3):
                    showPlayers();
                    break;
                case (4):
                    rankPlayer();
                    break;
                case (5):
                    cli.print("Introduce el nombre del jugador");
                    setScore(cli);
                    break;
                case (6):
                    break;
                case (7):
                    break;
                case (8):
                    break;
                case (9):
                    break;
                case (10):
                    break;
                default:
                    cli.print("El indice introducido no es correcto");
            }
        }
    }
    public void createPlayer(){


    }
    public void removePlayer(String name){

    }
    public void showPlayers(){

    }
    public void setScore(String name,int score){

    }
    public void rankPlayer(){

    }
    public void showMatchups(){

    }
    public void clearMatchups(){

    }
    public boolean matchPlayers(String name1,String name2){
        return true;
    }
    public void randomMatchup(){

    }
    public void terminate(){

    }

}
