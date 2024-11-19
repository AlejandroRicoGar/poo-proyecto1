package upm.model;

import java.util.ArrayList;
import java.util.List;

public class Team implements Member{
    private String name;
    private ArrayList<Player> teammates;

    private ArrayList<Tournament> tournaments;
    private Admin creator;

    public Team(String name,Admin creator) {
        this.name = name;
        this.teammates = new ArrayList<>();
        this.tournaments = new ArrayList<>();
        this.creator=creator;
    }
    @Override
    public String getName() {
        return name;
    }

    public Boolean isMember(Player player) {
        return teammates.contains(player);
    }
    public Boolean isMember(String playerID) {
        boolean resul = false;
        for(Player player: teammates){
            if(player.getId().equals(playerID)){
                resul=true;
                break;
            }
        }
        return resul;
    }

    public void addMember(Player player) {
        teammates.add(player);
    }
    @Override
    public Double getCategoryValue(Categories categoria) {
        Double total = 0.0;
        for(Player member : teammates) {
            total *= member.getCategoryValue(categoria);
        }
        return Math.pow(total,((double) 1 / teammates.size()));
    }

    @Override
    public void setCategoryValue(Categories category, Double value) {
        for (Player member : teammates) {
            member.setCategoryValue(category, value);
        }
    }
    @Override
    public void addTournament(Tournament tournament) {
        for(Player member : teammates) {
            member.addTournament(tournament);
        }
    }

    @Override
    public void deleteTournament(Tournament tournament) {
        for(Player member : teammates) {
            member.deleteTournament(tournament);
        }
    }

    @Override
    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public Admin getCreator() {
        return creator;
    }
}