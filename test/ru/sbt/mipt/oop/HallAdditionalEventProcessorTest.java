package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.processors.HallAdditionalEventProcessor;
import ru.sbt.mipt.oop.readers.SmartHomeJsReader;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

import static org.junit.Assert.*;

public class HallAdditionalEventProcessorTest {

    SmartHome smartHome;
    HallAdditionalEventProcessor hallAdditionalEventProcessor;

    @Before
    public void init() throws IOException {
        SmartHomeJsReader smartHomeJsReader = new SmartHomeJsReader();
        smartHome = smartHomeJsReader.readSmartHome();
        hallAdditionalEventProcessor = new HallAdditionalEventProcessor();
    }

    @Test
    public void HallEventSimpleTestClose() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");
        hallAdditionalEventProcessor.processEvent(event, smartHome);
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertEquals(false, light.getState());
            }
        }
    }

    @Test
    public void HallEventSimpleTestOpenClose() {
        SensorEvent event1 = new SensorEvent(SensorEventType.DOOR_OPEN, "4");
        SensorEvent event2 = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");
        hallAdditionalEventProcessor.processEvent(event1, smartHome);
        hallAdditionalEventProcessor.processEvent(event2, smartHome);
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                assertEquals(false, light.getState());
            }
        }
    }
}