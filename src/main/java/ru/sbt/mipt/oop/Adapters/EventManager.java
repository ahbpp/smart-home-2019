package ru.sbt.mipt.oop.Adapters;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.processors.EventProcessor;

public interface EventManager {
    void Loop();
    void addHomeEventsProcessor(EventProcessor eventProcessor, SmartHome smartHome);
}