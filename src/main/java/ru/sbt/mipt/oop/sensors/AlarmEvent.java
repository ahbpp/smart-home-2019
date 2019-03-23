package ru.sbt.mipt.oop.sensors;

public class AlarmEvent extends SensorEvent{

    private final int password;


    public AlarmEvent(SensorEventType type, int password) {
        super(type, "Alarm");
        this.password = password;
    }

    public int getPassword(){
        return this.password;
    }
}
