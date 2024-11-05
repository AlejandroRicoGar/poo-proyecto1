package upm.model;

import upm.model.category.Categoria;

import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> members;

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public void addMember(Player player) {
        members.add(player);
    }
    public Double getCategory(Categorys categoria) {
        System.out.println(name);
        Double total = 0.0;
        for(Player member : members) {
            total += member.getCategory(categoria);
        }
        System.out.println(total);
        System.out.println(members.size());
        Double resul = total/members.size();
        System.out.println(resul);
        return resul;
    }
}
