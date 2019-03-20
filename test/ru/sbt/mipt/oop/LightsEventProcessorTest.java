package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.processors.LightsEventProcessor;
import ru.sbt.mipt.oop.readers.SmartHomeJsReader;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

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
        Light light = smartHome.getLightByld("1");
        assertEquals(true, light.getState());

    }

    @Test
    public void LightsEventSimpleTurnOff() {
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "2");
        lightsEventProcessor.processEvent(event, smartHome);
        Light light = smartHome.getLightByld("2");
        assertEquals(false, light.getState());

    }
}