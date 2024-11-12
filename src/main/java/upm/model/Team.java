package upm.model;

import java.util.ArrayList;
import java.util.List;

public class Team implements Member{
    private String name;
    private ArrayList<Player> members;
    private ArrayList<Tournament> tournaments;

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.tournaments = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public Boolean isMember(Player player) {
        return members.contains(player);
    }

    public void addMember(Player player) {
        members.add(player);
    }

    public Double getCategory(Categories categoria) {
        Double total = 0.0;
        for(Player member : members) {
            total += member.getCategory(categoria);
        }
        Double resul = total/members.size();
        return resul;
    }

    @Override
    public void setCategory(Categories category, Double value) {
        for (Player member : members) {
            member.setCategory(category, value);
        }
    }


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

}
