package upm.command;

public class exit implements Command {


    /**
     * @param params Division por espacios el param[0] es el comando en si y el param[1] son los argumentos
     * @retrn output del comando
     */
    @Override
    public String apply(String[] params) {
        return "Exiting the app";
    }

    /**
     * @return la parte que se va a comparara del comando
     */
    @Override
    public String getCommand() {
        return "exit";
    }
    @Override
    public String toString() {
        return ">exit ";
    }

}
