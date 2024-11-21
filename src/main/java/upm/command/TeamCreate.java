package upm.command;

import upm.controller.PlayerController;
import upm.controller.PublicController;
import upm.controller.TeamController;
import upm.model.Player;
import upm.model.Team;
/**
 * Class to create a team
 */
public class TeamCreate implements Command{

    /**
     * Creates a team if it doesn't already exist
     * @param stringsep An array with the parameters [team-create, teamname;player1Id;player2Id]
     * @return The output of the command
     */
    @Override
    public String apply(String[] stringsep) {
            String output = "";
            if(stringsep.length == 2) {
                String[] params = stringsep[1].split(";");
                if(params.length==3){
                    if(TeamController.getInstance().searchTeam(stringsep[1])==null) {
                        Player p1=PlayerController.getInstance().search(params[1]);
                        Player  p2=PlayerController.getInstance().search(params[2]);
                        if((p1!=null)&&(p2!=null)){
                            TeamController.getInstance().addTeam(new Team(params[0],p1,p2,PublicController.getInstance().getAdmin(PublicController.getInstance().getLogged())));
                            output = "Created team "+params[0];
                        }else
                            output="One of the players doesnt exist";
                    }
                    else
                        output="Team already exists";
                }else
                    output = "Incorrect number of parameters: expected team name, player1 Id and player2 Id";
            }
            else{
                output = "Incorrect syntax: expected command param1;param2;param3";
            }
            return output;
    }
    /**
     * @return String with command description
     */
    @Override
    public String toString() {
        return "> team-create teamname;player1Id;player2Id";
    }
    /**
     * @return the name of the command
     */
    @Override
    public String getCommand() {
        return "team-create";
    }
}
