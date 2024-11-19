package upm.model;

import java.util.ArrayList;
import java.util.List;

public class Team implements Member{
    private String name;
    private ArrayList<Player> members;

    private ArrayList<Tournament> tournaments;
    private Admin creator;

    public Team(String name,Admin creator) {
        this.name = name;
        this.members = new ArrayList<>();
        this.tournaments = new ArrayList<>();
        this.creator=creator;
    }
    @Override
    public String getName() {
        return name;
    }

    public Boolean isMember(Player player) {
        return members.contains(player);
    }
    public Boolean isMember(String playerID) {
        boolean resul = false;
        for(Player player: members){
            if(player.getId().equals(playerID)){
                resul=true;
                break;
            }
        }
        return resul;
    }

    public void addMember(Player player) {
        members.add(player);
    }
    @Override
    public Double getCategoryValue(Categories categoria) {
        Double total = 0.0;
        for(Player member : members) {
            total *= member.getCategoryValue(categoria);
        }
        return Math.pow(total,((double) 1 / members.size()));
    }

    @Override
    public void setCategoryValue(Categories category, Double value) {
        for (Player member : members) {
            member.setCategoryValue(category, value);
        }
    }
    @Override
    public void addTournament(Tournament tournament) {
        for(Player member : members) {
            member.addTournament(tournament);
        }
    }

    @Override
    public void deleteTournament(Tournament tournament) {
        for(Player member : members) {
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