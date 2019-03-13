package ru.sbt.mipt.oop.readers;

import ru.sbt.mipt.oop.components.SmartHome;

import java.io.IOException;

public interface SmartHomeReader {
    SmartHome readSmartHome() throws IOException;
}
