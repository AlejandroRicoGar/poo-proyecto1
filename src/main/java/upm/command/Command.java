package upm.command;

public interface Command {

    public String apply(String[] params);
    public String toString();
    public  String getCommand();
}
