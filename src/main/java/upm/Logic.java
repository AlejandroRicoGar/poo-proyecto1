package upm;

import java.sql.SQLOutput;
import java.util.*;

public class Logic {
    private ArrayList<Match> matchList;
    private ArrayList<Player> playerList;
    private final CLI cli;
    private static final String CREATE_USER = "create";
    private static final String REMOVE_USER = "remove";
    private static final String SET_SCORE_USER = "score";
    private static final String SHOW_USERS = "show";
    private static final String RANK_USERS = "rank";
    private static final String MATCHMAKE_USERS = "matchmake";
    private static final String SHOW_MATCHMAKE_USERS = "show_matchmake";
    private static final String MATCHMAKE_USERS_RANDOMLY = "random_matchmake";
    private static final String CLEAR_MATCHMAKE = "clear_matchmake";

    private static final String COMMAND_ERROR = "command arguments incorrect";

    private static final String EXIT = "exit";

    public Logic(CLI cli) {
        this.cli = cli;
    }

    /**
     * Metodo que inicializa el programa
     * Primero crea una instancia de CLI
     * Luego inicializa y añade en las Arraylist los ejemplos requeridos
     * Para finalizar comienza el menu de ejecuacion
     */
    public void start() {
        this.matchList = new ArrayList<>();
        this.playerList = new ArrayList<>();

        Player Luisa = new Player("Luisa", 4.5);
        Player Manuel = new Player("Manuel", 2.7);
        Player Kurt = new Player("Kurt", 4.0);
        Player Sofia = new Player("Sofia", 3.8);
        Player Robert = new Player("Robert", 3.8);


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
    public String createPlayer(String[] args) {
        String output;
        boolean esNombre = false;
        boolean yesOrNo = false;
        if (args.length == 1) {
            String name = args[0];
            if (cli.isName(name)) {
                esNombre = true;
                if (!exists(name)) {
                    Player player = new Player(name);
                    playerList.add(player);
                    output = ("Jugador " + player.getName() + " creado correctamente\n");
                } else {
                    output = ("El jugador ya existe \n");
                }
            } else {
                output = (name + " no es un nombre valido\n");
            }
        } else output = COMMAND_ERROR;
        return output;
    }

    /**
     * @param args Nombre del jugador a eliminar
     *             Metodo que crea un jugador siempre que este no exista anteriormente
     */
    public String removePlayer(String[] args) {
        String output;
        if (args.length == 1) {
            String name = args[0];
            if (exists(name)) {
                Player player = searchPlayer(name);
                if (player != null) {
                    if (playerList.remove(player)) {
                        removeMatch(player);
                        output = ("Jugador " + name + " eliminado correctamente\n");
                    } else {
                        output = ("Hubo un problema eliminando el jugador\n");
                    }
                } else {
                    output = ("Hubo un problema eliminando el jugador\n");
                }
            } else {
                output = ("El jugador " + name + " no existe\n");
            }
        } else output = COMMAND_ERROR;
        return output;

    }

    /**
     * Elimina los emparejamientos que contienen al jugador
     *
     * @param player Jugador que se va a eliminar de los emparejamientos
     */
    public void removeMatch(Player player) {
        Iterator<Match> iter = matchList.iterator();
        while (iter.hasNext()) {
            Match match = iter.next();
            if (match.getPlayer1().equals(player) || match.getPlayer2().equals(player)) {
                iter.remove();
            }
        }
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
     */
    public String setScore(String[] args) {
        String output = "";
        if (args.length == 2) {
            String name = args[0];
            Double score = Double.valueOf(args[1]);
            Player player = searchPlayer(name);
            if (player != null) {
                if (score > -999999.0) {
                    player.setScore(score);
                    System.out.println(player.toString());
                } else {
                    output = ("Puntuacion inferior a -999999.0 no es valida\n");
                }
            } else {
                output = ("El jugador " + name + " no existe\n");
            }
        } else {
            output = COMMAND_ERROR;
        }
        return output;
    }

    /**
     * Metodo que crea un clon de la lista de jugadores, la ordena según puntuación en orden descendiente
     * e imprime el resultado ordenado por pantalla
     */
    public void rankPlayers() {
        ArrayList<Player> sortedPlayerList = new ArrayList<>(playerList);
        sortedPlayerList.sort((Player p1, Player p2) -> p2.getScore().compareTo(p1.getScore()));
        for (Player player : sortedPlayerList) {
            cli.print(player.toString() + "\n");
        }
    }

    /**
     * Imprime por pantalla el listado de enfrentamientos existentes,
     * si no hay emparejamientos imprime un mensaje de no hay emparejamientos
     */
    public String showMatchups() {
        String output = " ";
        if (matchList.isEmpty()) {
            output = ("No hay emparejamientos\n");
        } else {
            Iterator<Match> iter = matchList.iterator();
            while (iter.hasNext()) {
                cli.print(iter.next().toString() + "\n");
            }
        }
        return output;
    }

    /**
     * Elimina los emparejamientos existentes de matchList
     */
    public String clearMatchups() {
        matchList.clear();
        return ("Eliminados todos los emparejamientos correctamente\n");
    }

    /**
     * Metodo crea un nuevo objeto emparejamiento entre dos jugadores existentes
     */
    public String matchPlayers(String[] args) {
        String output = "";
        if (args.length == 2) {
            String name1 = args[0];
            String name2 = args[1];
            if (exists(name1) && exists(name2)) {
                Match match = new Match(searchPlayer(name1), searchPlayer(name2));
                matchList.add(match);
                output = ("Jugadores " + name1 + " y " + name2 + " correctamente emparejados\n");
            } else {
                output = ("No se ha podido realizar el emparejamiento\n");
            }
        } else output = COMMAND_ERROR;
        return output;
    }

    /**
     * Empareja aleatoriamente a los jugadores en parejas.
     * Si el número de jugadores es impar, indica que no se puede ejecutar la operación.
     */

    public String randomMatchup() {
        String output = "Emparejamientos realizados con exito";
        if (playerList.size() % 2 != 0) {
            output = ("No se pueden emparejar todos los jugadores (numero de jugadores debe ser par)\n");
        } else {
            List<Player> playerCopy = new ArrayList<>(playerList);
            Collections.shuffle(playerCopy);
            for (int i = 0; i < playerList.size(); i += 2) {
                String[] players = {playerCopy.get(i).getName(), playerCopy.get(i + 1).getName()};
                matchPlayers(players);
            }
        }
        return output;
    }

    private boolean exists(String name) {
        Iterator<Player> iter = playerList.iterator();
        boolean exists = false;
        while (!exists && iter.hasNext()) {
            if (iter.next().getName().equals(name)) {
                exists = true;
            }
        }
        return exists;
    }

    private Player searchPlayer(String name) {
        Iterator<Player> iter = playerList.iterator();
        boolean exists = false;
        Player player = null;
        while (!exists && iter.hasNext()) {
            Player aux = iter.next();
            if (aux.getName().equals(name)) {
                player = aux;
                exists = true;
            }
        }
        return player;
    }

    private void menu() {
        boolean exit = false;
        while (!exit) {
            System.out.print("#>");
            String[] input = cli.scanner().nextLine().split(" ");
            // check input format
            if (!input[0].equals(EXIT)) {
                if (input.length > 0) {
                    String command = input[0];
                    String[] arguments = {};
                    if (input.length > 1)
                        arguments = input[1].split(";");
                    exit = command.equals(EXIT);
                    router(command, arguments);
                } else {
                    System.out.println("Ha habido un error al realizar la accion");
                }
            } else {
                exit = true;
            }
        }
    }

    private void router(String command, String[] arguments) {
        String output = "";
        if (command.equals(CREATE_USER)) {
            output = createPlayer(arguments);
        } else if (command.equals(REMOVE_USER)) {
            output = removePlayer(arguments);
        } else if (command.equals(SHOW_USERS)) {
            showPlayers();
        } else if (command.equals(RANK_USERS)) {
            rankPlayers();
        } else if (command.equals(SET_SCORE_USER)) {
            output = setScore(arguments);
        } else if (command.equals(MATCHMAKE_USERS)) {
            output = matchPlayers(arguments);
        } else if (command.equals(SHOW_MATCHMAKE_USERS)) {
            output = showMatchups();
        } else if (command.equals(MATCHMAKE_USERS_RANDOMLY)) {
            output = randomMatchup();
        } else if (command.equals(CLEAR_MATCHMAKE)) {
            output = clearMatchups();
        } else {
            output = getHelp();
        }

        cli.print(output);
    }

    private String getHelp() {
        StringBuilder str = new StringBuilder("Available commands: \n");
        str.append("\tCreate player: ").append(CREATE_USER).append(" [player] for instance ").append(CREATE_USER).append(" Luisa\n");
        str.append("\tRemove player: ").append(REMOVE_USER).append(" [player] for instance ").append(REMOVE_USER).append(" Manuel\n");
        str.append("\tShow players:").append(SHOW_USERS).append("\n");
        str.append("\tRank players: ").append(RANK_USERS).append("\n");
        str.append("\tSet score for player: ").append(SET_SCORE_USER).append(" [player];[score] for instance ").append(SET_SCORE_USER).append(" Luisa;3.0\n");
        str.append("\tMatchmake players: ").append(MATCHMAKE_USERS).append(" [player1];[player2] for instance ").append(MATCHMAKE_USERS).append(" Luisa;Manuel\n");
        str.append("\tMatchmake players randomly: ").append(MATCHMAKE_USERS_RANDOMLY).append(" (an even number of players must exist)\n");
        str.append("\tClear Matchmake: ").append(CLEAR_MATCHMAKE).append("\n");
        str.append("\tShow matchmaking: ").append(SHOW_MATCHMAKE_USERS).append("\n");

        return str.toString();
    }
}


