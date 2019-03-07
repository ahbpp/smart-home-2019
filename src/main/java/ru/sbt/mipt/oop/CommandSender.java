package ru.sbt.mipt.oop;

public class CommandSender implements SmartHomeCommandSender {
    public void sendMessage(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
