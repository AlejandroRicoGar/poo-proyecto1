package upm;

import upm.command.*;
import upm.view.CLI;
import upm.controller.AdminController;
import  upm.controller.PlayerController;
import  upm.controller.PublicController;
import upm.view.Users;


import java.util.LinkedList;
import java.util.List;

public class App {

    private AdminController admin;
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
        admin = new AdminController(false);
        player = new PlayerController(false);
        publicController = new PublicController(player,admin);
        commands = new LinkedList<>();
        commandsPublic = new LinkedList<>();
        commandsPlayer = new LinkedList<>();
        commandsAdmin = new LinkedList<>();


        commandsPublic.add(new LogIn(publicController));
        commandsPublic.add(new Logout(publicController));
        commandsPublic.add(new TournamentList(publicController));


        commandsPlayer.add(new TournamentAdd(player));
        commandsPlayer.add(new TournamentRemove(player));
        commandsPlayer.add(new StaticsShow(player));


        commandsAdmin.add(new PlayerCreate(admin));
        commandsAdmin.add(new TeamCreate(admin));
        commandsAdmin.add(new PlayerDelete(admin));
        commandsAdmin.add(new TeamDelete(admin));
        commandsAdmin.add(new TeamAdd(admin));
        commandsAdmin.add(new TeamRemove(admin));
        commandsAdmin.add(new TournamentCreate(admin));
        commandsAdmin.add(new TournamentDelete(admin));
        commandsAdmin.add(new TournamentDelete(admin));

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

            if(admin.isLoggedin()){
                for (Command command : commandsAdmin) {
                    commandsList.add(command.toString());
                    Permitedcommands.add(command);
                }
                for(Command command : commandsPublic){
                    commandsList.add(command.toString());
                    Permitedcommands.add(command);
                }
            } else if (player.isLogged()) {
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
