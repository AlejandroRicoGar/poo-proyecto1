package upm.command;

import upm.controller.PlayerController;
import upm.controller.PublicController;

public class PlayerCreate implements Command{
    private PlayerController playerController;
    private PublicController publicController;

    public PlayerCreate(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public String apply(String[] stringsep) {
        String output = "";
        String[] params = stringsep[1].split(";");
        if(params.length == 5) {
            output = playerController.createPlayer(params[0], params[1], params[2], params[3], params[4], publicController.getAdmin(publicController.getLogged()));
        }
        return output;
    }

    @Override
    public String toString() {
        return "> player-create name;surname;id;password;email";
    }

    @Override
    public String getCommand() {
        return "player-create ";
    }
}
