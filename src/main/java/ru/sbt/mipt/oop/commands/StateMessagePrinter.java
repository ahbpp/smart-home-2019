package ru.sbt.mipt.oop.commands;

public class StateMessagePrinter implements SmartHomeMessagePrinter {
    @Override
    public void sendMessage(String command) { System.out.println(command); }
}
