package upm.command;

import upm.controller.PublicController;

import java.sql.SQLOutput;

public class LogIn extends Command{
    private PublicController controller;

    public LogIn(PublicController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        String output = "";
        if(params.length == 2 ){
            String[] args = params[1].split(";");
            if(args.length == 2) {
                output = controller.login(args);
            }
        }else{
            output = "Incorrect number of arguments";
        }
        return output;
    }

    @Override
    public String toString() {
        return "> login email;password ";
    }

    @Override
    public String getCommand() {
        return "login";
    }
}
