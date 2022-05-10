package rDarkTablets;

public abstract class Task {
    protected String name;

    public Task(String name) {
        this.name = name;
    }

    public abstract boolean activate();
    public abstract void execute();

}
