package upm;

import upm.controller.PlayerController;
import upm.controller.PublicController;
import upm.controller.TournamentController;
import upm.model.*;

public class Init {
    private PublicController publicController;
    private TournamentController tournamentController;

    public Init(PublicController publicController,TournamentController tournamentController) {
        this.publicController = publicController;
        this.tournamentController = tournamentController;
    }

    public void start(){
        Admin Admin = new Admin("A","B");
        publicController.signUpUser(Admin);
        Player Alejandro = new Player("Alejandro","Rico", "123456789","UPM","alejandro.ricog@alumnos.es",Admin);
        publicController.signUpUser(Alejandro);
        Alejandro.setCategory(Categorys.SCORED_POINTS,3.0);
        Player Alfonso = new Player("Alfonso","Garcia","123456789","UPM1","ABCD",Admin);
        Alfonso.setCategory(Categorys.SCORED_POINTS,2.0);
        Tournament Individual = new Tournament("Open de Cuenca","06/07/2005","24/12/2025","Primera Division","Ping Pong",Categorys.SCORED_POINTS,TournamentTypes.INDIVIDUAL);
        tournamentController.AddTournament(Individual);
        Individual.addPlayer(Alfonso);
        Individual.addPlayer(Alejandro);
        Player Alfredo = new Player("Alfredo","Garcia","123456789","UPM1","ABCD",Admin);
        Alfredo.setCategory(Categorys.SCORED_POINTS,5.0);
        Individual.addPlayer(Alfredo);
        System.out.println("Alfredo");
        System.out.println(Alfredo.getCategory(Categorys.SCORED_POINTS));
        System.out.println("Alejandro");
        System.out.println(Alejandro.getCategory(Categorys.SCORED_POINTS));
        System.out.println("Alfonso");
        System.out.println(Alfonso.getCategory(Categorys.SCORED_POINTS));
        Team equipo1 = new Team("Equipo 1");
        Team equipo2 = new Team("Equipo 2");
        Tournament Colectivo = new Tournament("Copa del rey","06/07/2005","24/12/2025","Primera Division","Ping Pong",Categorys.SCORED_POINTS,TournamentTypes.COLECTIVO);
        tournamentController.AddTournament(Colectivo);
        equipo1.addMember(Alfredo);
        equipo2.addMember(Alejandro);
        equipo1.addMember(Alfredo);
        Colectivo.addTeam(equipo1);
        Colectivo.addTeam(equipo2);

    }

}
