package upm.command;

import upm.controller.TeamController;
import upm.model.Team;
/**
 * Class to delete a team
 */
public class TeamDelete implements Command{
    /**
     * Team controller to delete team from list of teams
     */
    private TeamController teamController;
    /**
     * Constructor of the class
     * @param tcontroller Team controller to delete the team from list of teams
     */

    public TeamDelete(TeamController tcontroller) {
        this.teamController = tcontroller;
    }
    /**
     * Deletes a team if it exists, and it doesn't have a tournament
     * @param stringsep An array with the parameters [team-create, name]
     * @return The output of the command
     */
    @Override
    public String apply(String[] stringsep) {
        String output = "";
        if(stringsep.length == 2) {
            Team team=teamController.searchTeam(stringsep[1]);
            if(team!=null) {
                if(team.getTournaments().isEmpty()) {
                    teamController.deleteTeam(team);
                    output = "Team deleted";
                }
                else {
                    output="Team cannot be deleted if it is in a tournament";
                }
            }
            else {
                output="Team doesnt exist";
            }
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
        return "> team-delete name";
    }
    /**
     * @return name of the command
     */

    @Override
    public String getCommand() {
        return "team-delete";
    }
}
