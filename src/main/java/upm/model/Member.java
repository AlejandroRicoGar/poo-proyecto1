package upm.model;

public interface Member {
    public String getName();
    public Double getCategory(Categories category);
    public void setCategory(Categories category,Double value);
    public void addTournament(Tournament tournament);
    public void deleteTournament(Tournament tournament);


}
