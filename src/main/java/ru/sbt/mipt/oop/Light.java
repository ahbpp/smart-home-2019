package ru.sbt.mipt.oop;

public class Light {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void turnOff() {
        this.setOn(false);
        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, this.getId());
        CommandSender commandSender = new CommandSender();
        commandSender.sendCommand(command);
    }

    public boolean getState() {
        return isOn;
    }
}
