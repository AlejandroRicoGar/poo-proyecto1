package upm;

import java.util.Scanner;

public class CLI {
    /**
     * Crea un nuevo objeto Scanner para recibir inputs
     *
     * @return  un nuevo objeto Scanner
     */
    public Scanner scanner() {
        return new Scanner(System.in);
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
     * Verifica si una cadena es un nombre válido.
     * Un nombre válido es una cadena que solo contiene letras.
     *
     * @param s la cadena a verificar
     * @return true si la cadena es válida, false de lo contrario
     */
    public boolean esNombre(String s){
        boolean esNombre = true;
        String[] espacios = s.split(" ");
        if(espacios.length!=1){
            esNombre = false;
        }
        if(!s.matches("[a-zA-Z]+")){
            esNombre = false;
        }
        return esNombre;
    }
}
