package upm.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.List;


public interface Member {
    public String getName();
    public Double getCategoryValue(Categories category);
    public void setCategoryValue(Categories category,Double value);
    public void addTournament(Tournament tournament);
    public void deleteTournament(Tournament tournament);
    public List<Tournament> getTournaments();
    public boolean contains(Player p);
}
