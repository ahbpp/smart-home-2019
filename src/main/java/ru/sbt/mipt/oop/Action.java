package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.components.Actionable;

public interface Action {
    void execute(Actionable actionable);
}
