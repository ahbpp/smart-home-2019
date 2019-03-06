package ru.sbt.mipt.oop;

public interface EventProcessor {
    void runEvent(SensorEvent event, SmartHome smartHome);
}
