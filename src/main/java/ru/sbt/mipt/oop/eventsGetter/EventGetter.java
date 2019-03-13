package ru.sbt.mipt.oop.eventsGetter;

import ru.sbt.mipt.oop.sensors.SensorEvent;

public interface EventGetter {
    SensorEvent getNextSensorEvent();
}
