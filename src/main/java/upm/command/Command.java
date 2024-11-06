package upm.command;

public interface Command {

    /**
     * @param params Division por espacios el param[0] es el comando en si y el param[1] son los argumentos
     * @retrn  output del comando
     */
    public String apply(String[] params);

    /**
     * @return el comando que se va a mostrar por pantalla, contiene un ejemplo de como se deben pasar los argumentos
     */
    public String toString();

    /**
     * @return la parte que se va a comparara del comando
     */
    public  String getCommand();
}
