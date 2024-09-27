package upm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Logic {
    private ArrayList<Match> matchList;
    private ArrayList<Player> playerList;
    private CLI cli;

    /**
     * Metodo que inicializa el programa
     * Primero crea una instancia de CLI
     * Luego inicializa y añade en las Arraylist los ejemplos requeridos
     * Para finalizar comienza el menu de ejecuacion
     */
    public void start(){
        this.cli = new CLI();


        Player Luisa = new Player("Luisa",4.5);
        Player Manuel = new Player("Manuel",2.7);
        Player Kurt = new Player("Kurt",4.0);
        Player Sofia = new Player("Sofia",3.8);
        Player Robert = new Player("Robert",3.8);

        matchList = new ArrayList<>();
        playerList = new ArrayList<>();

        playerList.add(Luisa);
        playerList.add(Manuel);
        playerList.add(Kurt);
        playerList.add(Sofia);
        playerList.add(Robert);

        menu();
    }

    /**
     * Metodo que crea un jugador
     * Comprueba si el nombre existe y es unico y crea el jugador
     */
    public void createPlayer(){
        boolean esNombre = false;
        while(!esNombre) {
            cli.print("Introduce el nombre del nuevo jugador: ");
            String name = cli.scanner().nextLine();
            if(cli.esNombre(name)) {
                esNombre = true;
                if (!exists(name)) {
                    Player player = new Player(name);
                    playerList.add(player);
                    cli.print("Jugador " + player.getName() + " creado correctamente\n");
                } else {
                    cli.print("El jugador ya existe \n");
                }
            }else {
                cli.print("¿Quiere volver a intentar Y/N ");
                if(cli.scanner().nextLine().equals("N")){
                    esNombre = true;
                }
            }
        }
    }

    /**
     * @param name Nombre del jugador a eliminar
     * Metodo que crea un jugador siempre que este no exista anteriormente
     */
    public void removePlayer(String name){
        if(exists(name)){
            Player player = searchPlayer(name);
            if(player != null) {
                if(playerList.remove(player)) {
                    cli.print("Jugador " + name + " eliminado correctamente: ");
                }else{
                    cli.print("Hubo un problema eliminando el jugador\n");
                }
            }else{
                cli.print("Hubo un problema eliminando el jugador\n");
            }
        }else{
            cli.print("El jugador "+name+" no existe\n");
        }
    }
    public void showPlayers(){
        Iterator<Player> iter = playerList.iterator();
        while(iter.hasNext()){
            cli.print(iter.next().toString()+"\n");
        }
    }
    public void setScore(String name,double score){
        Player player=searchPlayer(name);
        if(player!=null){
            if(score>-999999.0){
                player.setScore(score);
            }
        }
    }
    public void rankPlayers(){
        ArrayList<Player> sortedPlayerList= (ArrayList<Player>) playerList.clone();
        for(int j=sortedPlayerList.size()-1;j>0;j--){
            for(int i=0;i<j;i++){
                if(sortedPlayerList.get(i).getScore()>sortedPlayerList.get(i+1).getScore()){
                    Player aux=sortedPlayerList.get(i);
                    sortedPlayerList.set(i,sortedPlayerList.get(i+1));
                    sortedPlayerList.set(i+1,aux);
                }
            }
        }
        Iterator<Player> iter = sortedPlayerList.iterator();
        while(iter.hasNext()){
            Player player=iter.next();
            cli.print(player.getName()+" ("+player.getScore()+") \n");
        }
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
    private void menu(){
        boolean resume = true;
        while(resume) {
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
            int index = cli.scanner().nextInt();
            switch (index) {
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
                    rankPlayers();
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
                    resume = false;
                    break;
                default:
                    cli.print("El indice introducido no es correcto");
            }
        }

    }

}
