package upm.command;

import upm.controller.TournamentController;

public class TournamentCreate implements Command{
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        if(stringsep.length == 2) {
            String[] params = stringsep[1].split(";");
            if (params.length == 6) {
                output = TournamentController.getInstance().createTournament(params);
            } else {
                output = "Incorrect number of parameters";
            }
        }else {
            output = "Incorrect number of parameters";
        }
        return output;
    }

    /*
     *  \n    Name must not have spaces \n    Dates Format DD/MM/YYYY\n    Categories:\n     >SCORED_POINTS,\n" +
                "     >MATCH_WON,\n" +
                "     >ASISTS_POINTS,\n" +
                "     >PAST_TOURNAMENTS,\n" +
                "     >GENERATED_MONEY "
     */
    @Override
    public String toString() {
        return "> tournament-create name;startDate;endDate;sport;league;category";
    }

    @Override
    public String getCommand() {
        return "tournament-create";
    }
}
