package upm.command;

public abstract class Command {

    public abstract String apply(String[] stringsep);
    public abstract String toString();
    public abstract String getCommand();
}
