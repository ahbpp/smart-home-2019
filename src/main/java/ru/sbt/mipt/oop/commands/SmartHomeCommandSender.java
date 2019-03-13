package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.sensors.SensorCommand;

public interface SmartHomeCommandSender {
    void sendCommand(SensorCommand command);
}
