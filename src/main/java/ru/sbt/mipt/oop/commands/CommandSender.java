package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.sensors.SensorCommand;

public class CommandSender implements SmartHomeCommandSender {
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
