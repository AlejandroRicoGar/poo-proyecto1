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

    private TeamController team;
    private TournamentController tournament;
    private PlayerController player;
    private PublicController publicController;
    private CLI cli;
    private List<Command> commandsPublic;
    private List<Command> commandsAdmin;
    private List<Command> commandsPlayer;
    private List<Command> commands;


    /**
     * Inicia la aplicacion
     * @param args
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
        cli = new CLI();
        player = new PlayerController();
        publicController = new PublicController();
        tournament = new TournamentController();
        team = new TeamController();

        commands = new LinkedList<>();
        commandsPublic = new LinkedList<>();
        commandsPlayer = new LinkedList<>();
        commandsAdmin = new LinkedList<>();


        commandsPublic.add(new LogIn(publicController));
        commandsPublic.add(new Logout(publicController));
        commandsPublic.add(new TournamentList(tournament,publicController));


        commandsPlayer.add(new TournamentAdd(tournament,team,publicController));
        commandsPlayer.add(new TournamentRemove(tournament,publicController));
        commandsPlayer.add(new StaticsShow(player));


        commandsAdmin.add(new PlayerCreate(player));
        commandsAdmin.add(new TeamCreate(team));
        commandsAdmin.add(new PlayerDelete(player));
        commandsAdmin.add(new TeamDelete(team));
        commandsAdmin.add(new TeamAdd(team));
        commandsAdmin.add(new TeamRemove(team));
        commandsAdmin.add(new TournamentCreate(tournament));
        commandsAdmin.add(new TournamentDelete(tournament));
        commandsAdmin.add(new TournamentMatchMaking(tournament));

        for (Command command : commandsPublic) {
            commands.add(command);
        }
        for (Command command : commandsAdmin) {
            commands.add(command);
        }
        for (Command command : commandsPlayer) {
            commands.add(command);
        }

        Init i = new Init(publicController,tournament,team);
        i.start();
    }

    //TODO: SE TENDRA QUE DIVIDIR EN DOS SUBMETODOS PARA REDUCIR COMPLEJIDAD

    /**
     * Crea dos listas, una de Strings para mostrar los comandos permitidos y otra de comandos para añadir los objetos
     * comandos.
     * > Comandos publicos seran permitidos para los casos: Not Logged,Player, Admin
     * > Comandos player seran permitidos para los casos: PLayer
     * > Comandos Admin seran permitidos para los casos: Admin
     *
     * Posteriormente llama al CLI para obtener el comando que se escribe por pantalla y comprueba comando a comando
     * permitido (lista PermitedCommands). El output sera lo que va a devolver el comando.
     *
     */
    private void start(){
        boolean finish = false;
        while(!finish){
            List<String> commandsList=new LinkedList<>();
            List<Command> Permitedcommands=new LinkedList<>();

            if(publicController.isAdmin()){
                for (Command command : commandsAdmin) {
                    commandsList.add(command.toString());
                    Permitedcommands.add(command);
                }
                for(Command command : commandsPublic){
                    commandsList.add(command.toString());
                    Permitedcommands.add(command);
                }
            } else if (publicController.isPlayer()) {
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

            String command = cli.getCommand(commandsList);
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
                    cli.print("Command not found \n");
                }else {
                    cli.print(output.toString()+"\n");
                }
            }

        }
    }
}
