package upm;

import upm.controller.PublicController;
import upm.controller.TeamController;
import upm.controller.TournamentController;
import upm.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Init {
    private PublicController publicController;
    private TournamentController tournamentController;
    private TeamController teamController;

    public Init(PublicController publicController,TournamentController tournamentController,TeamController teamController) {
        this.publicController = publicController;
        this.tournamentController = tournamentController;
        this.teamController = teamController;
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


        Alejandro.setCategory(Categories.SCORED_POINTS,3.0);
        Alfonso.setCategory(Categories.SCORED_POINTS,2.0);
        Alfredo.setCategory(Categories.SCORED_POINTS,5.0);

        //Creacion de dos Equipos nuevos
        Team equipo1 = new Team("Equipo_1");
        Team equipo2 = new Team("Equipo_2");
        equipo1.addMember(Alfredo);
        equipo2.addMember(Alejandro);
        equipo1.addMember(Alfredo);
        equipo2.addMember(Victor);
        teamController.addTeam(equipo1);
        teamController.addTeam(equipo2);

        //Creacion de dos torneos, uno individual y otro colectivo

        String fecha1 = "06/07/2005";
        String fin = "24/12/2025";

        // Definimos el formato de la fecha (día/mes/año)
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convertimos los strings a objetos LocalDate
        LocalDate fechaInicio = LocalDate.parse(fecha1, formato);
        LocalDate fechaFinal = LocalDate.parse(fin, formato);

        Tournament Individual = new Tournament("Open_de_Cuenca",fechaInicio,fechaFinal,"Primera Division","Ping Pong",Categories.SCORED_POINTS);
        tournamentController.addTournament(Individual);
        Individual.addMember(Alfonso);
        Individual.addMember(Alfredo);
        Individual.addMember(Victor);
        Individual.addMember(Alejandro);

        Tournament Colectivo = new Tournament("Copa_del_rey",fechaInicio,fechaFinal,"Primera Division","Ping Pong",Categories.SCORED_POINTS);
        tournamentController.addTournament(Colectivo);
        Colectivo.addMember(equipo1);

    }

}
