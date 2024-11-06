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



    public String getCommand(List<String> ListCommand) {
        print("Available commands: \n");
        for (String comandb: ListCommand)
            print(comandb.toString()+"\n");
        String input = sc.nextLine();
        return input;
    }



}
