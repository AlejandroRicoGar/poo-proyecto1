package upm.command;

import upm.controller.PublicController;
import upm.controller.TeamController;
import upm.controller.TournamentController;

public class TournamentAdd implements Command{


    @Override
    public String apply(String[] stringSep) {
        String output = "";
        if (stringSep.length == 2) {
            String[] params = stringSep[1].split(";");
            String tournamentName = params[0];
            if (params.length == 1) {
                output = TournamentController.getInstance().addMember(
                        TournamentController.getInstance().search(tournamentName),
                        PublicController.getInstance().getPlayer(PublicController.getInstance().getLogged())
                );
            } else if (params.length == 2) {
                output = TournamentController.getInstance().addTeam(
                        tournamentName,
                        TeamController.getInstance().searchTeam(params[1]),
                        PublicController.getInstance().getPlayer(PublicController.getInstance().getLogged())
                );
            } else {
                output = "Incorrect number of parameters";
            }
        } else {
            output = "Incorrect number of parameters";
        }
        return output;
    }

    @Override
    public String toString() {
        return "> tournament-add Tournamentname / tournament-add Tournamentname;teamName";
    }

    @Override
    public String getCommand() {
        return "tournament-add";
    }
}
