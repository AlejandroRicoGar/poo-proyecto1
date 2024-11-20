package upm.command;

import upm.controller.PublicController;
import upm.controller.TournamentController;

public class TournamentRemove implements Command{
;
    @Override
    public String apply(String[] stringsep) {
        String output= "";
        if(stringsep.length==2){
            String[] espacios = stringsep[1].split(";");
            if (espacios.length == 2) {
                output = TournamentController.getInstance().removeTeam(TournamentController.getInstance().search(espacios[0]), espacios[1],PublicController.getInstance().getPlayer(PublicController.getInstance().getLogged()));
            } else if (espacios.length == 1) {
                output = TournamentController.getInstance().removePlayer(PublicController.getInstance().getPlayer(PublicController.getInstance().getLogged()), TournamentController.getInstance().search(stringsep[1]));
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
        return "> tournament-remove teamName (optional)";
    }

    @Override
    public String getCommand() {
        return "tournament-remove";
    }
}
