package upm;

import java.util.*;

public class Logic {
    private  ArrayList<Match> matchList;
    private  ArrayList<Player> playerList;
    private final CLI cli;

    public Logic(CLI cli) {
        this.cli = cli;
    }

    /**
     * Metodo que inicializa el programa
     * Primero crea una instancia de CLI
     * Luego inicializa y añade en las Arraylist los ejemplos requeridos
     * Para finalizar comienza el menu de ejecuacion
     */
    public void start(){
        this.matchList = new ArrayList<>();
        this.playerList = new ArrayList<>();

        Player Luisa = new Player("Luisa",4.5);
        Player Manuel = new Player("Manuel",2.7);
        Player Kurt = new Player("Kurt",4.0);
        Player Sofia = new Player("Sofia",3.8);
        Player Robert = new Player("Robert",3.8);



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
        boolean yesOrNo = false;
        while(!esNombre) {
                cli.print("Introduce el nombre del nuevo jugador: ");
                String name = cli.scanner().nextLine();
                if (cli.isName(name)) {
                    esNombre = true;
                    if (!exists(name)) {
                        Player player = new Player(name);
                        playerList.add(player);
                        cli.print("Jugador " + player.getName() + " creado correctamente\n");
                    } else {
                        cli.print("El jugador ya existe \n");
                    }
                } else {
                    cli.print(name + " no es un nombre valido\n");
                    do {
                        cli.print("¿Desea volver a intentarlo? Y/n\n");
                        String input = cli.scanner().nextLine();
                        if (input.equalsIgnoreCase("n")) {
                            esNombre = true;
                            yesOrNo = true;
                        } else if (input.equalsIgnoreCase("y") || input.isEmpty()) {
                            yesOrNo = true;
                        }
                    } while (!yesOrNo);
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
                if(playerList.remove(player) && removeMatch(player)) {
                    cli.print("Jugador " + name + " eliminado correctamente\n");
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

    /**
     * Elimina los emparejamientos que contienen al jugador
     * @param player Jugador que se va a eliminar de los emparejamientos
     * @return true si el emparejamiento ha sido eliminado correctamente, false de lo contrario
     */
    public boolean removeMatch(Player player) {
        for (Match match : matchList) {
            if (match.getPlayer1().equals(player) || match.getPlayer2().equals(player)) {
                matchList.remove(match);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodo que muestra todos los jugadores
     */
    public void showPlayers() {
        Iterator<Player> iter = playerList.iterator();
        while (iter.hasNext()) {
            cli.print(iter.next().toString() + "\n");
        }
    }

    /**
     * Metodo que asigna una puntuación valida a un jugador,
     * en caso de puntuacion no valida imprime mensaje
     * @param name Nombre del jugador
     * @param score Puntuacion que se va a asignar al jugador
     *
     */
    public void setScore(String name,double score){
        Player player=searchPlayer(name);
        if(player!=null){
            if(score>-999999.0){
                player.setScore(score);
            }else{
                cli.print("Puntuacion inferior a -999999.0 no es valida\n");
            }
        } else {
            cli.print("El jugador "+name+" no existe\n");
        }
    }

    /**
     * Metodo que crea un clon de la lista de jugadores, la ordena según puntuación en orden descendiente
     * e imprime el resultado ordenado por pantalla
     */
    public void rankPlayers(){
        ArrayList<Player> sortedPlayerList= new ArrayList<>(playerList);
        sortedPlayerList.sort((Player p1,Player p2) -> p2.getScore().compareTo(p1.getScore()));
        /* Implementacion antigua con un Bubble Sort
        for(int j=sortedPlayerList.size()-1;j>0;j--){
            for(int i=0;i<j;i++){
                if(sortedPlayerList.get(i).getScore()<sortedPlayerList.get(i+1).getScore()){
                    Player aux=sortedPlayerList.get(i);
                    sortedPlayerList.set(i,sortedPlayerList.get(i+1));
                    sortedPlayerList.set(i+1,aux);
                }
            }
        }*/
        for (Player player : sortedPlayerList) {
            cli.print(player.toString() + "\n");
        }
    }
    /**
     * Imprime por pantalla el listado de enfrentamientos existentes,
     * si no hay emparejamientos imprime un mensaje de no hay emparejamientos
     */
    public void showMatchups(){
        if (matchList.isEmpty()){
            cli.print("No hay emparejamientos\n");
        }else{
            Iterator<Match> iter = matchList.iterator();
            while(iter.hasNext()){
               cli.print(iter.next().toString()+"\n");
            }
        }
    }

    /**
     * Elimina los emparejamientos existentes de matchList
     */
    public void clearMatchups(){
        matchList.clear();
        cli.print("Eliminados todos los emparejamientos correctamente\n");
    }

    /**
     * Metodo crea un nuevo objeto emparejamiento entre dos jugadores existentes
     * @param name1 Jugador 1 del enfrentamiento creado
     * @param name2 Jugador 2 del enfrentamiento creado
     */
    public void matchPlayers(String name1,String name2){
        if(exists(name1)&&exists(name2)){
            Match match=new Match(searchPlayer(name1),searchPlayer(name2));
            matchList.add(match);
            cli.print("Jugadores "+name1+" y "+name2+" correctamenete emparejados\n");
        }else{
            cli.print("No se ha podido realizar el emparejamiento\n");
        }

    }

    /**
     * Empareja aleatoriamente a los jugadores en parejas.
     * Si el número de jugadores es impar, indica que no se puede ejecutar la operación.
     */

    public void randomMatchup(){
        if (playerList.size() % 2 != 0) {
            cli.print("No se pueden emparejar todos los jugadores (numero de jugadores debe ser par)\n");
        } else {
            List<Player> playerCopy = new ArrayList<>(playerList);
            Collections.shuffle(playerCopy);
            for (int i = 0; i < playerList.size(); i += 2) {
                matchPlayers(playerCopy.get(i).getName(), playerCopy.get(i + 1).getName());
            }
        }

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
            try {
                int index = cli.scanner().nextInt();

                switch (index) {
                    case 1 -> createPlayer();
                    case 2 -> {
                        cli.print("Introduce el nombre del jugador: ");
                        removePlayer(cli.scanner().nextLine());
                    }
                    case 3 -> showPlayers();
                    case 4 -> rankPlayers();
                    case 5 -> {
                        cli.print("Introduce el nombre del jugador: ");
                        String name = cli.scanner().nextLine();
                        cli.print("Introduce la puntuacion: ");
                        setScore(name, cli.scanner().nextInt());
                    }
                    case 6 -> showMatchups();
                    case 7 -> clearMatchups();
                    case 8 -> {
                        cli.print("Introduce el nombre de el jugador1: ");
                        String name1 = cli.scanner().nextLine();
                        cli.print("Introduce el nombre de el jugador2: ");
                        matchPlayers(name1, cli.scanner().nextLine());
                    }
                    case 9 -> randomMatchup();
                    case 10 -> resume = false;
                    default -> cli.print("El indice introducido no es correcto\n");
                }
            }catch (InputMismatchException e){
                cli.print("Dato introducido no reconocido \n");
                menu();
            }
        }

    }

}
