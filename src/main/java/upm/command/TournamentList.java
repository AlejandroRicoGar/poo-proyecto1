package upm.command;

import upm.controller.TournamentController;
import upm.controller.PublicController;

public class TournamentList implements Command{
    private TournamentController controller;
    private PublicController publicController;
    public TournamentList(TournamentController controller, PublicController publicController) {
        this.controller = controller;
        this.publicController = publicController;
    }
    @Override
    public String apply(String[] params) {
        String output =  "";
        if(publicController.isAdmin()){
            output = controller.rankAndPrune();
        }else if(publicController.isPlayer()){
            output = controller.rank();
        }else{
            output = controller.show();
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
