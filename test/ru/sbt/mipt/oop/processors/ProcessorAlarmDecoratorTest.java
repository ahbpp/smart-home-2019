package ru.sbt.mipt.oop.processors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.readers.SmartHomeJsReader;
import ru.sbt.mipt.oop.sensors.AlarmEvent;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

import static org.junit.Assert.*;

public class ProcessorAlarmDecoratorTest {

    SmartHome smartHome;
    AlarmEventProcessor alarmEventProcessor;
    ProcessorAlarmDecorator doorEventProcessor;
    ProcessorAlarmDecorator lightsEventProcessor;

    @Before
    public void init() throws IOException {
        SmartHomeJsReader smartHomeJsReader = new SmartHomeJsReader();
        smartHome = smartHomeJsReader.readSmartHome();
        alarmEventProcessor = new AlarmEventProcessor();
        doorEventProcessor = new ProcessorAlarmDecorator(new DoorEventProcessor());
        lightsEventProcessor = new ProcessorAlarmDecorator( new LightsEventProcessor());
    }

    @Test
    public void AlertBlockTest() {
        AlarmEvent event1 = new AlarmEvent(SensorEventType.ALARM_ACTIVATE, 1234);
        alarmEventProcessor.processEvent(event1, smartHome);

        AlarmEvent event2 = new AlarmEvent(SensorEventType.ALARM_DEACTIVATE, 3457);
        alarmEventProcessor.processEvent(event2, smartHome);

        //alert state

        SensorEvent event3 = new SensorEvent(SensorEventType.DOOR_CLOSED, "3");
        doorEventProcessor.processEvent(event3, smartHome);
        Door door = smartHome.getDoorByld("3");
        assertEquals(true, door.getState());

        SensorEvent event4 = new SensorEvent(SensorEventType.LIGHT_OFF, "2");
        lightsEventProcessor.processEvent(event4, smartHome);
        Light light = smartHome.getLightByld("2");
        assertEquals(true, light.getState());
    }

    @Test
    public void AlertUnblockTest() {
        AlarmEvent event1 = new AlarmEvent(SensorEventType.ALARM_ACTIVATE, 1234);
        alarmEventProcessor.processEvent(event1, smartHome);

        AlarmEvent event2 = new AlarmEvent(SensorEventType.ALARM_DEACTIVATE, 3457);
        alarmEventProcessor.processEvent(event2, smartHome);

        //alert state

        AlarmEvent event4 = new AlarmEvent(SensorEventType.ALARM_DEACTIVATE, 1234);
        alarmEventProcessor.processEvent(event4, smartHome);

        //deactivate state

        SensorEvent event3 = new SensorEvent(SensorEventType.DOOR_CLOSED, "3");
        doorEventProcessor.processEvent(event3, smartHome);
        Door door = smartHome.getDoorByld("3");
        assertEquals(false, door.getState());

        SensorEvent event5 = new SensorEvent(SensorEventType.LIGHT_OFF, "2");
        lightsEventProcessor.processEvent(event5, smartHome);
        Light light = smartHome.getLightByld("2");
        assertEquals(false, light.getState());
    }
}