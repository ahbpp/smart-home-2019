package ru.sbt.mipt.oop;

public class Door implements Actionable{
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);


    }

    @Override
    public String getComponentName() {
        return getClass().getName();
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean getState() {
        return isOpen;
    }
}
