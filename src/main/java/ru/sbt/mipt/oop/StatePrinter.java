package ru.sbt.mipt.oop;

public class StatePrinter implements SmartHomePrinter {
    @Override
    public void sendMessage(String command) { System.out.println(command); }
}
