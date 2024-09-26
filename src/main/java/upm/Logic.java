package upm;

import java.util.ArrayList;
import java.util.Iterator;

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
                    String name = cli.scanner().nextLine();
                    cli.print("Introduce la puntuacion");
                    setScore(name,cli.scanner().nextInt());
                    break;
                case (6):
                    showMatchups();
                    break;
                case (7):
                    clearMatchups();
                    break;
                case (8):
                    cli.print("Introduce el nombre de el jugador1");
                    String name1 = cli.scanner().nextLine();
                    cli.print("Introduce el nombre de el jugador2");
                    matchPlayers(name1,cli.scanner().nextLine());
                    break;
                case (9):
                    randomMatchup();
                    break;
                case (10):
                    seguir = false;
                    break;
                default:
                    cli.print("El indice introducido no es correcto");
            }
        }
    }
    public void createPlayer(){
        cli.print("Introduce el nombre del nuevo jugador: ");
        String name = cli.scanner().nextLine();
        if(!exists(name) && cli.esNombre(name)) {
            Player player = new Player(name);
            playerList.add(player);
            cli.print("Jugador "+player.getName()+" creado correctamente\n");
        }else{
            cli.print("El jugador ya existe \n");
        }
    }
    public void removePlayer(String name){
        if(exists(name)){
            Player player = searchPlayer(name);
            if(player != null) {
                playerList.remove(player);
                cli.print("Jugador " + name + " eliminado correctamente\n");
            }else{
                cli.print("Hubo un problema eliminando el jugador");
            }
        }else{
            cli.print("El jugador "+name+" no existe\n");
        }

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

    private boolean exists(String name){
        Iterator<Player> iter  = playerList.iterator();
        boolean exists = false;
        while(!exists && iter.hasNext()){
            if(iter.next().getName().equals(name)){
                exists = true;
            }
        }
        return exists;
    }
    private Player searchPlayer(String name){
        Iterator<Player> iter  = playerList.iterator();
        boolean exists = false;
        Player player = null;
        while(!exists && iter.hasNext()){
            Player aux = iter.next();
            if(aux.getName().equals(name)){
                player = aux;
                exists = true;
            }
        }
        return player;
    }

}
