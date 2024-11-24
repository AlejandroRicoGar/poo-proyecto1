package upm;

import upm.controller.PlayerController;
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
    private PlayerController playerController;

    public Init(PublicController publicController,TournamentController tournamentController,TeamController teamController, PlayerController playerController){
        this.publicController = publicController;
        this.tournamentController = tournamentController;
        this.teamController = teamController;
        this.playerController = playerController;
    }

    /**
     * Metodo para insertar datos en la Aplicacion
     */
    public void start(){
        //Crea admin general
        Admin Admin = new Admin("A","B");
        publicController.signUpUser(Admin); //Registra el admin añadiendolo a la lista de usuarios y de admins

        //Creacion de jugadores
        playerController.createPlayer("Alejandro","Rico", "12","UPM","alejandro.ricog@alumnos.es", Admin);
        Player alejandro = playerController.search("12");
        //Hacer lo mismo para el resto de jugadores
        playerController.createPlayer("Alfonso","Garcia", "123","UPM","alfonso.garcia@alumnos.es", Admin);
        Player alfonso = playerController.search("123");
        playerController.createPlayer("Alfredo","Garcia", "1234","UPM","alfredo.garcia@alumnos.es", Admin);
        Player alfredo = playerController.search("1234");
        playerController.createPlayer("Victor","Palmier", "12345","UPM","victor.palmier@alumnos.es", Admin);
        Player victor = playerController.search("12345");
        playerController.createPlayer("Pepe", "Santos", "43", "UCM", "pepe.santos@alumnos.es", Admin);
        Player pepe = playerController.search("43");

        alejandro.setCategoryValue(Categories.SCORED_POINTS,3.0);
        alfonso.setCategoryValue(Categories.SCORED_POINTS,2.0);
        alfredo.setCategoryValue(Categories.SCORED_POINTS,5.0);

        //Creacion de dos Equipos nuevos
        Team equipo1 = new Team("Equipo_1",alfredo,victor,Admin);
        Team equipo2 = new Team("Equipo_2",alejandro,alfredo,Admin);
        equipo1.addMember(alejandro);
        equipo1.addMember(pepe);
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
        Individual.addMember(alfonso);
        Individual.addMember(alfredo);
        Individual.addMember(victor);
        Individual.addMember(alejandro);

        Tournament Colectivo = new Tournament("Copa_del_rey",fechaInicio,fechaFinal,"Primera Division","Ping Pong",Categories.SCORED_POINTS);
        tournamentController.addTournament(Colectivo);
        Colectivo.addMember(equipo1);

    }

}
