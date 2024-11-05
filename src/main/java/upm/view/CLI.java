package upm.view;

import java.util.List;
import java.util.Scanner;

public class CLI {

    private Scanner sc;

    @Deprecated
    /**
     * TODO: METODO QUE NO SE VA A VOLVER A UTILIZAR
     * Crea un nuevo objeto Scanner para recibir inputs
     *
     * @return  un nuevo objeto Scanner
     */

    public Scanner scanner() {
        return new Scanner(System.in);
    }


     public CLI(){
         sc = new Scanner(System.in);
     }
    /**
     * Imprime el string dado.
     *
     * @param s el string a imprimir
     */
    public void print(String s) {
        System.out.print(s);
    }


    /**
     * TODO: HAY QUE CAMBIARLO AL CONTROLADOR DE USUARIO O DE ADMIN CUANDO LLEGUEMOS
     * Verifica si una cadena es un nombre válido.
     * Un nombre válido es una cadena que solo contiene letras.
     *
     * @param s la cadena a verificar
     * @return true si la cadena es válida, false de lo contrario
     */
    public boolean isName(String s){
        boolean isName = true;
        String[] space = s.split(" ");
        if(space.length!=1){
            isName = false;
        }
        if(!s.matches("[a-zA-Z]+")){
            isName = false;
        }
        return isName;
    }
    public String getCommand(List<String> ListCommand) {
        print("Available commands: \n");
        for (String comandb: ListCommand)
            print(comandb.toString());
        String input = sc.nextLine();
        return input;
    }



}
