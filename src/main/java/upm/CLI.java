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

    /**
     * @param s nombre para comprobar
     * @return si el nombre es una sola palabra
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
}
