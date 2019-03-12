package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LightsEventProcessorTest {


    SmartHome smartHome;
    LightsEventProcessor lightsEventProcessor;

    @Before
    public void init() throws IOException {
        SmartHomeJsReader smartHomeJsReader = new SmartHomeJsReader();
        smartHome = smartHomeJsReader.readSmartHome();
        lightsEventProcessor = new LightsEventProcessor();
    }

    @Test
    public void LightsEventSimpleTurnOn() {
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        lightsEventProcessor.processEvent(event, smartHome);
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals("1")) {
                    assertEquals(true, light.getState());
                }
            }
        }

    }

    @Test
    public void LightsEventSimpleTurnOff() {
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "2");
        lightsEventProcessor.processEvent(event, smartHome);
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals("2")) {
                    assertEquals(false, light.getState());
                }
            }
        }

    }
}