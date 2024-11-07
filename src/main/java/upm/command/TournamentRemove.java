package upm.command;

import upm.controller.TournamentController;

public class TournamentRemove implements Command{
    private TournamentController controller;
    public TournamentRemove(TournamentController controller) {
        this.controller = controller;
    }
    @Override
    public String apply(String[] stringsep) {
        String output= "";
        if(stringsep.length==2) {
            output = controller.remove(stringsep[1]);
        }else{
            output= "Incorrect number of arguments";
        }
        return output;
    }

    @Override
    public String toString() {
        return "tournament-remove username/teamName";
    }

    @Override
    public String getCommand() {
        return "tournament-remove";
    }
}
