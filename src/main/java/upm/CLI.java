package upm;

import java.util.Scanner;

public class CLI {
    /**
     * Creates a new Scanner to read inputs.
     *
     * @return  a new Scanner object
     */
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    /**
     * Prints the given string.
     *
     * @param s the string to be printed
     */
    public void print(String s) {
        System.out.print(s);
    }
    public boolean esNombre(String s){
        boolean esNombre = true;
        String[] espacios = s.split(" ");
        if(espacios.length!=1){
            esNombre = false;
        }
        return esNombre;
    }
}
