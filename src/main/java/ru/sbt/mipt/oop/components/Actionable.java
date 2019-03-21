package ru.sbt.mipt.oop.components;


import ru.sbt.mipt.oop.Action;

public interface Actionable {
    void execute(Action action);
    String getComponentName();

}
