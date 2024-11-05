package upm;

import upm.controller.PlayerController;
import upm.controller.PublicController;
import upm.model.Admin;
import upm.model.Player;
import upm.model.User;

public class Init {
    private PublicController publicController;

    public Init(PublicController publicController) {
        this.publicController = publicController;
    }

    public void start(){
        User Alejandro = new Player("Alejandro","Rico", "123456789","UPM","alejandro.ricog@alumnos.es");
        publicController.signUpUser(Alejandro);
        User Admin = new Admin("A","B");
        publicController.signUpUser(Admin);
    }

}
