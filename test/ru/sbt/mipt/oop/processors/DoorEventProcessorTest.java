package ru.sbt.mipt.oop.processors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.readers.SmartHomeJsReader;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DoorEventProcessorTest {

    SmartHome smartHome;
    DoorEventProcessor doorEventProcessor;

    @Before
    public void init() throws IOException {
        SmartHomeJsReader smartHomeJsReader = new SmartHomeJsReader();
        smartHome = smartHomeJsReader.readSmartHome();
        doorEventProcessor = new DoorEventProcessor();
    }

    @Test
    public void processEventSimpleClose() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "3");
        doorEventProcessor.processEvent(event, smartHome);
        Door door = smartHome.getDoorByld("3");
        assertEquals(false, door.getState());

    }

    @Test
    public void processEventSimpleOpen() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        doorEventProcessor.processEvent(event, smartHome);
        Door door = smartHome.getDoorByld("1");
        assertEquals(true, door.getState());
    }

}