package upm;

import upm.command.*;
import upm.controller.TeamController;
import upm.controller.TournamentController;
import upm.model.Team;
import upm.model.Tournament;
import upm.view.CLI;
import  upm.controller.PlayerController;
import  upm.controller.PublicController;


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



    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

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
        commandsPublic.add(new TournamentList(tournament));


        commandsPlayer.add(new TournamentAdd(tournament));
        commandsPlayer.add(new TournamentRemove(tournament));
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

        Init i = new Init(publicController,admin,player);
        i.start();
    }

    //TODO: SE TENDRA QUE DIVIDIR EN DOS SUBMETODOS PARA REDUCIR COMPLEJIDAD

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
                    input = c.apply(args);
                    if (input!=null) {
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
