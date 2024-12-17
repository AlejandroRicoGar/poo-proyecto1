package upm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Team implements Member{
    @Id
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "teams")
    private ArrayList<Player> members;

    @ManyToMany(mappedBy = "members")
    private ArrayList<Tournament> tournaments;

    @ManyToOne()
    @JoinColumn
    private Admin creator;

    public Team(String name,Player p1,Player p2,Admin creator) {
        this.name = name;
        this.members = new ArrayList<>();
        members.add(p1);
        members.add(p2);
        this.tournaments = new ArrayList<>();
        this.creator=creator;
    }

    public Team() {}

    @Override
    public String getName() {
        return name;
    }
    public int getTeamSize(){return members.size();}

    public Boolean isMember(Player player) {
        return members.contains(player);
    }
    public Player getMember(String playerID) {
        Player resul=null;
        for(Player player: members){
            if(player.getId().equals(playerID)){
                resul=player;
                break;
            }
        }
        return resul;
    }


    public void addMember(Player player) {
        members.add(player);
    }
    public void deleteMember(Player player){
        members.remove(player);
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

    @Override
    public boolean contains(Player p) {
        boolean contains = false;
        for(Player member : members) {
            if(member.getId().equals(p.getId())) {
                contains = true;
            }
        }
        return contains;
    }
}