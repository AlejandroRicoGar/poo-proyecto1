package upm.view;

import java.util.List;
import java.util.Scanner;

public class CLI {

    private static CLI ints;

    private Scanner sc;

    @Deprecated
    /**
     * Crea un nuevo objeto Scanner para recibir inputs
     *
     * @return  un nuevo objeto Scanner
     */

    public Scanner scanner() {
        return new Scanner(System.in);
    }


     private CLI(){
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
    public static CLI getInstance(){
        if (ints == null)
            ints = new CLI();
        return ints;
    }




}
