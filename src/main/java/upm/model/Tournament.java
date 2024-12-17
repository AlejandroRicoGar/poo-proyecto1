package upm.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name = "endDate")
    private LocalDate endDate;
    @Column(name = "league")
    private String league;
    @Column(name = "sport")
    private String sport;
    @Column(name = "categoria")
    private Categories categoria;
    @ManyToMany()
    @JoinTable(name="members")
    private ArrayList<Member> members;
    @OneToMany(mappedBy = "tournament")
    private ArrayList<Matchmaking> matches;

    /**
     * @param name Name of the tournament
     * @param startDate Date of the begging of the tournament
     * @param endDate  Date of the end of the tournament
     * @param league    League of the tournament
     * @param sport Sport that is played in the tournament
     * @param categoria Category that is used to rank in the tournament
     */
    public Tournament(String name, LocalDate startDate, LocalDate endDate, String league, String sport, Categories categoria) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.league = league;
        this.sport = sport;
        this.categoria = categoria;

        members = new ArrayList<Member>();
        matches = new ArrayList<Matchmaking>();
    }
    public Tournament() {}

    public LocalDate getEndDate() {
        return endDate;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Matchmaking> getMatches() {
        return matches;
    }

    public Categories getCategoria() {
        return categoria;
    }

    /**
     * @param m player to be added to the tournament
     */
    public void addMember(Member m) {
        if(m != null) {
            members.add(m);
            m.addTournament(this);
        }
    }
    /**
     * @return all the members of a tournament
     */
    public String showMembers(){
        StringBuilder builder = new StringBuilder();
            for(Member m : members){
                builder.append( m.getName()+"  ");
            }
        return builder.toString();
    }

    public Member searchMember(String name) {
        Iterator<Member> iter = members.iterator();
        boolean exists = false;
        Member sol = null;
        while (!exists && iter.hasNext()) {
            Member m = iter.next();
            if (m.getName().equals(name)) {
                sol = m;
                exists = true;
            }
        }
        return sol;
    }


    public void deleteTeam(Team team) {
        members.remove(team);
    }

    public void deletePlayer(Player player) {
        members.remove(player);
    }

    public void clearMathchups(){
        matches.clear();
    }

    public void addMatchups(Matchmaking m) {
        matches.add(m);
    }



}
