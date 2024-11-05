package upm.model;

import upm.model.category.Categoria;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Tournament {
    private String name;
    private Double startDate;
    private Double endDate;
    private String league;
    private String sport;
    private String categoria;
    private TournamentTypes type;
    private ArrayList<Player> players;
    private ArrayList<Team> teams;

    public Tournament(String name, Double startDate, Double endDate, String league, String sport, String categoria,TournamentTypes type) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.league = league;
        this.sport = sport;
        this.categoria = categoria;
        this.type = type;

        if(type.equals(TournamentTypes.INDIVIDUAL)){
            players = new ArrayList<Player>();
        }else{
            teams = new ArrayList<Team>();
        }
    }

}
