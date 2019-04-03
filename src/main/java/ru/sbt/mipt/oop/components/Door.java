package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.sensors.SensorCommand;

public class Door implements Actionable {
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

    public void close() {
        this.setOpen(false);
        SensorCommand command = new SensorCommand(CommandType.DOOR_CLOSED, this.getId());
        CommandSender commandSender = new CommandSender();
        commandSender.sendCommand(command);
    }
}
