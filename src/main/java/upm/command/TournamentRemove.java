package upm.command;

import upm.controller.PublicController;
import upm.controller.TournamentController;

public class TournamentRemove implements Command{
    private TournamentController controller;
    private PublicController controllerPublic;

    public TournamentRemove(TournamentController controller, PublicController controllerPublic) {
        this.controller = controller;
        this.controllerPublic = controllerPublic;
    }
    @Override
    public String apply(String[] stringsep) {
        String output= "";
        if(stringsep.length==2){
            String[] espacios = stringsep[1].split(";");
            if (espacios.length == 2) {
                output = controller.removeTeam(controller.search(espacios[0]), espacios[1],controllerPublic.getPlayer(controllerPublic.getLogged()));
            } else if (espacios.length == 1) {
                output = controller.removePlayer(controllerPublic.getPlayer(controllerPublic.getLogged()), controller.search(stringsep[1]));
            } else {
                output = "Incorrect number of arguments";
            }
        }else{
             output = "Incorrect number of arguments";
        }
        return output;
    }

    @Override
    public String toString() {
        return "> tournament-remove username/teamName";
    }

    @Override
    public String getCommand() {
        return "tournament-remove";
    }
}
