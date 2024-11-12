package upm.model;

import java.util.List;

public interface Member {
    public String getName();
    public Double getCategory(Categories category);
    public void setCategory(Categories category,Double value);
    public void addTournament(Tournament tournament);
    public void deleteTournament(Tournament tournament);
    public List<Tournament> getTournaments();
}
