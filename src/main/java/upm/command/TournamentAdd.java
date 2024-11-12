package upm.command;

import upm.controller.PublicController;
import upm.controller.TeamController;
import upm.controller.TournamentController;


import java.util.ArrayList;

public class TournamentAdd implements Command{
    private TournamentController controller;
    private TeamController controllerTeam;
    private PublicController controllerPublic;

    public TournamentAdd(TournamentController controller, TeamController controllerTeam, PublicController controllerPublic) {
        this.controller = controller;
        this.controllerTeam = controllerTeam;
        this.controllerPublic = controllerPublic;
    }

    @Override
    public String apply(String[] stringsep) {
        String output = "";
        String[] params = stringsep[1].split(";");
        String tournament = params[0];
        if(params.length == 1) {
            output = controller.addMember(controller.search(tournament),controllerPublic.getPlayer(controllerPublic.getLogged()));
        }else if(params.length == 2) {
            System.out.println(params[1]);
            output = controller.addTeam(tournament,controllerTeam.searchTeam(params[1]),controllerPublic.getPlayer(controllerPublic.getLogged()));
        }else{
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
