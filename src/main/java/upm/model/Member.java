package upm.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


public interface Member {

    public String getName();
    public Double getCategoryValue(Categories category);
    public void setCategoryValue(Categories category,Double value);
    public void addTournament(Tournament tournament);
    public void deleteTournament(Tournament tournament);
    public Set<Tournament> getTournaments();
    public boolean contains(Player p);
}
