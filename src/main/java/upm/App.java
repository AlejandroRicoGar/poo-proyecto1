package upm;

import upm.command.*;
import upm.controller.TeamController;
import upm.controller.TournamentController;
import upm.view.CLI;
import upm.controller.PlayerController;
import upm.controller.PublicController;


import java.util.LinkedList;
import java.util.List;

public class App {
    //200
    private final List<Command> commandsPublic;
    private final List<Command> commandsAdmin;
    private final List<Command> commandsPlayer;
    private List<Command> commands;


    /**
     * Inicia la aplicacion
     * @param args constructor
     */
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    /**
     * Iniciliza las controladoras y el CLI, añade los comandos a las listas de los tipos de usuarios de la aplicacion,
     * para que pueda mostrar los comandos permitidos para cada usuario. Tambien inicializa init introduciendo los datos
     * de la aplicacion.
     */
    public App(){
        commands = new LinkedList<>();
        commandsPublic = new LinkedList<>();
        commandsPlayer = new LinkedList<>();
        commandsAdmin = new LinkedList<>();


        commandsPublic.add(new LogIn());
        commandsPublic.add(new Logout());
        commandsPublic.add(new TournamentList());


        commandsPlayer.add(new TournamentAdd());
        commandsPlayer.add(new TournamentRemove());
        commandsPlayer.add(new StatisticsShow());


        commandsAdmin.add(new PlayerCreate());
        commandsAdmin.add(new TeamCreate());
        commandsAdmin.add(new PlayerDelete());
        commandsAdmin.add(new TeamDelete());
        commandsAdmin.add(new TeamAdd());
        commandsAdmin.add(new TeamRemove());
        commandsAdmin.add(new TournamentCreate());
        commandsAdmin.add(new TournamentDelete());
        commandsAdmin.add(new TournamentMatchMaking());

        commands.addAll(commandsPublic);
        commands.addAll(commandsAdmin);
        commands.addAll(commandsPlayer);

        Init i = new Init(PublicController.getInstance(),TournamentController.getInstance(),TeamController.getInstance(), PlayerController.getInstance());
        i.start();
    }

    //TODO: SE TENDRA QUE DIVIDIR EN DOS SUBMETODOS PARA REDUCIR COMPLEJIDAD

    /**
     * Crea dos listas, una de Strings para mostrar los comandos permitidos y otra de comandos para añadir los objetos
     * comandos.
     * > Comandos publicos seran permitidos para los casos: Not Logged,Player, Admin
     * > Comandos player seran permitidos para los casos: PLayer
     * > Comandos Admin seran permitidos para los casos: Admin
     * Posteriormente llama al CLI para obtener el comando que se escribe por pantalla y comprueba comando a comando
     * permitido (lista PermitedCommands). El output sera lo que va a devolver el comando.
     *
     */
    private void start(){
        boolean finish = false;
        while(!finish){
            List<String> commandsList=new LinkedList<>();
            List<Command> Permitedcommands=new LinkedList<>();

            if(PublicController.getInstance().isAdmin()){
                for (Command command : commandsAdmin) {
                    commandsList.add(command.toString());
                    Permitedcommands.add(command);
                }
                for(Command command : commandsPublic){
                    commandsList.add(command.toString());
                    Permitedcommands.add(command);
                }
            } else if (PublicController.getInstance().isPlayer()) {
                for (Command command : commandsPlayer) {
                    commandsList.add(command.toString());
                    Permitedcommands.add(command);
                }
                for(Command command : commandsPublic){
                    commandsList.add(command.toString());
                    Permitedcommands.add(command);
                }
            }else {
                for (Command command : commandsPublic) {
                    commandsList.add(command.toString());
                    Permitedcommands.add(command);
                }
            }

            String command = CLI.getInstance().getCommand(commandsList);
            if(command.equals("exit")){
                finish = true;
            }else {
                StringBuffer output=new StringBuffer();
                String[] args = command.split(" ");
                String input = "";
                for (Command c:Permitedcommands) {
                    if(c.getCommand().equals(args[0])){
                        input = c.apply(args);
                        output.append(input);
                    }
                }
                if (output.toString().isEmpty()){
                    CLI.getInstance().print("Command not found \n");
                }else {
                    CLI.getInstance().print(output.toString()+"\n");
                }
            }
            CLI.getInstance().print("------------------------------------\n");
        }
    }
}
