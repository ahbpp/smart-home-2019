package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;

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
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("3")) {
                    assertEquals(false, door.getState());
                }
            }
        }

    }

    @Test
    public void processEventSimpleOpen() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        doorEventProcessor.processEvent(event, smartHome);
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("1")) {
                    assertEquals(true, door.getState());
                }
            }
        }

    }
}