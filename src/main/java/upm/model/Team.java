package upm.model;

import upm.model.category.Category;

import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> members;

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(Player player) {
        members.add(player);
    }
    public Double getCategory(Category category) {
        return 0.0;
    }
}
