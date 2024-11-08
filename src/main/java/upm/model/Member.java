package upm.model;

public interface Member {
    public String getName();
    public Double getCategory(Categorys category);
    public void setCategory(Categorys category,Double value);
    public void addTournament(Tournament tournament);
    public void deleteTournament(Tournament tournament);


}
