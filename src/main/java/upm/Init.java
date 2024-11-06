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

    /**
     * Metodo para insertar datos en la Aplicacion
     */
    public void start(){
        //Crea admin general
        Admin Admin = new Admin("A","B");
        publicController.signUpUser(Admin); //Registra el admin añadiendolo a la lista de usuarios y de admins

        //Creacion de jugadores
        Player Alejandro = new Player("Alejandro","Rico", "123456789","UPM","alejandro.ricog@alumnos.es",Admin);
        Player Alfonso = new Player("Alfonso","Garcia","123456789","UPM1","ABCD",Admin);
        Player Alfredo = new Player("Alfredo","Garcia","123456789","UPM1","ABCD",Admin);
        Player Victor = new Player("Victor","Palmier","123456789","UPM1","ABCD",Admin);


        publicController.signUpUser(Alejandro);
        publicController.signUpUser(Alfonso);
        publicController.signUpUser(Alfredo);
        publicController.signUpUser(Victor);


        Alejandro.setCategory(Categorys.SCORED_POINTS,3.0);
        Alfonso.setCategory(Categorys.SCORED_POINTS,2.0);
        Alfredo.setCategory(Categorys.SCORED_POINTS,5.0);

        //Creacion de dos Equipos nuevos
        Team equipo1 = new Team("Equipo_1");
        Team equipo2 = new Team("Equipo_2");
        equipo1.addMember(Alfredo);
        equipo2.addMember(Alejandro);
        equipo1.addMember(Alfredo);
        equipo2.addMember(Victor);



        //Creacion de dos torneos, uno individual y otro colectivo

        Tournament Individual = new Tournament("Open_de_Cuenca","06/07/2005","24/12/2025","Primera Division","Ping Pong",Categorys.SCORED_POINTS,TournamentTypes.INDIVIDUAL);
        tournamentController.AddTournament(Individual);
        Individual.addPlayer(Alfonso);
        Individual.addPlayer(Alejandro);
        Individual.addPlayer(Alfredo);
        Individual.addPlayer(Victor);

        Tournament Colectivo = new Tournament("Copa_del_rey","06/07/2005","24/12/2025","Primera Division","Ping Pong",Categorys.SCORED_POINTS,TournamentTypes.COLECTIVO);
        tournamentController.AddTournament(Colectivo);
        Colectivo.addTeam(equipo1);
        Colectivo.addTeam(equipo2);

    }

}
