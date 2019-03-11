package ru.sbt.mipt.oop;

public class StateMessagePrinter implements SmartHomeMessagePrinter {
    @Override
    public void sendMessage(String command) { System.out.println(command); }
}
