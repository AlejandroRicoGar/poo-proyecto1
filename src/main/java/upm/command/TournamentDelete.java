package upm.command;

import upm.controller.TournamentController;

public class TournamentDelete implements Command {
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        if(stringsep.length == 2) {
            output = TournamentController.getInstance().delete(stringsep[1]);
        }
        else{
            output = "Incorrect number of parameters, only the name of the tournament is required";
        }
        return output;
    }

    @Override
    public String toString() {
        return "> tournament-delete tournamentname";
    }

    @Override
    public String getCommand() {
        return "tournament-delete";
    }
}
