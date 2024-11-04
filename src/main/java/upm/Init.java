package upm.App;

import upm.controller.AdminController;
import upm.controller.PlayerController;
import upm.controller.PublicController;
import upm.model.User;
import upm.view.Users;

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
        User Alejandro = new User("UPM","alejandro.ricog@alumnos.es", Users.ADMIN);
        publicController.signUpUser(Alejandro);
    }


}
