package upm.command;

import upm.controller.TournamentController;

public class TournamentMatchMaking implements Command {
    private TournamentController controller;

    public TournamentMatchMaking(TournamentController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] stringsep) {
        String output = "";
        if(stringsep[1].equals("-m")){
            String[] args = stringsep[2].split(";");
            if(args.length%2 != 0){
                output = controller.manualMatchmaking(args);
            }else{
                output = "The number of members that is going to be matchmaked is not even";
            }
        } else if (stringsep[1].equals("-a")) {


        }else{
            output = "You must select a matchmaking option -a for automatic matchmaking and -m for manual matchmaking";
        }
        return output;
    }

    @Override
    public String toString() {
        return "> tournament-matchmaking -m tournamentName;MembersName1;MemberName2....  /   tournament-matchmaking -a tournamentName";
    }

    @Override
    public String getCommand() {
        return "tournament-matchmaking";
    }
}
