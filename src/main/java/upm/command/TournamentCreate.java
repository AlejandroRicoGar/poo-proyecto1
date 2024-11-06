package upm.command;

import upm.controller.TournamentController;

public class TournamentCreate implements Command{
    private TournamentController controller;
    public TournamentCreate(TournamentController controller) {
        this.controller = controller;
    }
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        String[] params = stringsep[1].split(";");
        if(params.length == 7){
            output = controller.createTournament(params);
        }else {
            output = "Incorrect number of parameters";
        }

        return output;
    }

    @Override
    public String toString() {
        return "> tournament-create name;startDate;endDate;league;sport;category;type \n    Name must not have spaces \n    Dates Format DD/MM/YYYY    Categories:\n     >SCORED_POINTS,\n" +
                "     >MATCH_WON,\n" +
                "     >ASISTS_POINTS,\n" +
                "     >PAST_TOURNAMENTS,\n" +
                "     >GENERATED_MONEY \n    Types:\n     >INDIVIDUAL,\n" +
                "     >COLECTIVE";
    }

    @Override
    public String getCommand() {
        return "tournament-create";
    }
}
