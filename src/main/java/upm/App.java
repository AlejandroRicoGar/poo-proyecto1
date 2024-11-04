package upm.App;

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
    private Users users;


    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public App(){
        cli = new CLI();
        admin = new AdminController();
        player = new PlayerController();
        publicController = new PublicController();
        users = Users.PUBLIC;
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
    }

    public void changeStatus(Users usuario){
        this.users = usuario;
    }

    private void start(){
        boolean finish = false;
        List<String> commandsList=new LinkedList<>();

        while(!finish){
            if(users.equals(users.ADMIN)){
                for (Command command : commandsAdmin) {
                    commandsList.add(command.toString());
                }
            } else if (users.equals(users.PUBLIC)) {
                for (Command command : commandsPublic) {
                    commandsList.add(command.toString());
                }

            }else {
                for (Command command : commandsPlayer) {
                    commandsList.add(command.toString());
                }
            }

            String command = cli.getCommand(commandsList);
            if(command.equals("exit")){
                finish = true;
            }else {
                String input = cli.getCommand(commandsList);
                String[] params = input.split(" ");;
                String output = "";
                for(Command c : commands){
                    output = c.apply(params);
                }
                if(output.equals("")){
                    cli.print("Command not found");
                }
            }
        }
    }
}
