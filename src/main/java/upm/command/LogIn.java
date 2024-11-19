package upm.command;

import upm.controller.PublicController;

public class LogIn implements Command{
    private PublicController controller;

    public LogIn(PublicController controller) {
        this.controller = controller;
    }

    /**
     * Metodo que comprueba que los parametros que se pasan es correcto y que el usuario no estan logueado ya
     * @param params Division por espacios el param[0] es el comando en si y el param[1] son los argumentos
     * @return
     */
    @Override
    public String apply(String[] params) {
        String output = "";
        if(!controller.isAdmin() && !controller.isPlayer()) {
            if (params.length == 2) {
                String[] args = params[1].split(";");
                if (args.length == 2) {
                    output = controller.login(args);
                }else{
                    output = "Incorrect number of arguments.";
                }
            } else {
                output = "Incorrect number of arguments";
            }
        }else{
            output = "You are already logged in, if you wish to use another account logout and login again";
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
