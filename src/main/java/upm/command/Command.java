package upm.command;

public interface Command {

    public String apply(String[] stringsep);
    public String toString();
    public  String getCommand();
}
