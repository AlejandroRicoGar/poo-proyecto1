package upm;

import upm.controller.AdminController;
import upm.controller.PlayerController;
import upm.controller.PublicController;
import upm.model.Admin;
import upm.model.Player;
import upm.model.User;

public class Init {
    private PublicController publicController;
    private AdminController adminController;
    private PlayerController playerController;


    public Init(PublicController publicController, AdminController adminController, PlayerController playerController) {
        this.publicController = publicController;
        this.adminController = adminController;
        this.playerController = playerController;
    }

    public void start(){
        User Alejandro = new Player("Alejandro","Rico", "123456789","UPM","alejandro.ricog@alumnos.es");
        publicController.signUpUser(Alejandro);
        User Admin = new Admin("A","B");
        publicController.signUpUser(Admin);
    }

}
