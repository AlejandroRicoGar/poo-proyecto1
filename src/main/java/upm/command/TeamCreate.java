package upm.command;

import upm.controller.PublicController;
import upm.controller.TeamController;
import upm.model.Team;
/**
 * Class to create a team
 */
public class TeamCreate implements Command{
    /**
     * Team controller to create team
     */
    private TeamController teamController;
    /**
     * Public controller to get the admin
     */
    private PublicController publicController;
    /**
     * Constructor of the class
     * @param tcontroller Team controller to create the team
     * @param pcontroller Public controller to get the admin
     */
    public TeamCreate(TeamController tcontroller,PublicController pcontroller) {
        this.teamController = tcontroller;
        this.publicController = pcontroller;
    }
    /**
     * Creates a team
     * @param stringsep An array with the parameters [team-create, name]
     * @return The output of the command
     */
    @Override
    public String apply(String[] stringsep) {
            String output = "";
            if(stringsep.length == 2) {
                if(teamController.searchTeam(stringsep[1])==null)
                    output=teamController.addTeam(new Team(stringsep[1],publicController.getAdmin(publicController.getLogged())));
                else
                    output="Team already exists";
            }
            else{
                output = "Incorrect number of parameters, expected only team name";
            }
            return output;
    }
    /**
     * @return String with command description
     */
    @Override
    public String toString() {
        return "team-create name";
    }
    /**
     * @return the name of the command
     */
    @Override
    public String getCommand() {
        return "team-create";
    }
}
