package upm.command;

import upm.controller.TournamentController;
import upm.controller.PublicController;

public class TournamentList implements Command{
    @Override
    public String apply(String[] params) {
        String output =  "";
        if(PublicController.getInstance().isAdmin()){
            output = TournamentController.getInstance().rankAndPrune();
        }else if(PublicController.getInstance().isPlayer()){
            output = TournamentController.getInstance().rank();
        }else{
            output = TournamentController.getInstance().show();
        }
        return output;
    }

    @Override
    public String toString() {
        return "> tournament-list";
    }

    @Override
    public String getCommand() {
        return "tournament-list";
    }
}
