package ru.sbt.mipt.oop.processors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.readers.SmartHomeJsReader;
import ru.sbt.mipt.oop.sensors.AlarmEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class AlarmEventProcessorTest {

    SmartHome smartHome;
    AlarmEventProcessor alarmEventProcessor;

    @Before
    public void init() throws IOException {
        SmartHomeJsReader smartHomeJsReader = new SmartHomeJsReader();
        smartHome = smartHomeJsReader.readSmartHome();
        alarmEventProcessor = new AlarmEventProcessor();
    }

    @Test
    public void AlarmActivateSimpleTest() {
        AlarmEvent event = new AlarmEvent(SensorEventType.ALARM_ACTIVATE, 1234);
        alarmEventProcessor.processEvent(event, smartHome);
        assertTrue(smartHome.getAlarm().isActivateState());
    }

    @Test
    public void AlarmDeactivateSimpleTest(){
        AlarmEvent event1 = new AlarmEvent(SensorEventType.ALARM_ACTIVATE, 1234);
        alarmEventProcessor.processEvent(event1, smartHome);
        AlarmEvent event2 = new AlarmEvent(SensorEventType.ALARM_DEACTIVATE, 1234);
        alarmEventProcessor.processEvent(event2, smartHome);
        assertTrue(smartHome.getAlarm().isDeactivateState());
    }

    @Test
    public void AlarmWrongPasswordSimpleTest() {
        AlarmEvent event1 = new AlarmEvent(SensorEventType.ALARM_ACTIVATE, 1234);
        alarmEventProcessor.processEvent(event1, smartHome);
        AlarmEvent event2 = new AlarmEvent(SensorEventType.ALARM_DEACTIVATE, 3457);
        alarmEventProcessor.processEvent(event2, smartHome);
        assertTrue(smartHome.getAlarm().isAlertState());
    }

    @Test
    public void AlarmDeactivateTest(){
        AlarmEvent event1 = new AlarmEvent(SensorEventType.ALARM_ACTIVATE, 1234);
        alarmEventProcessor.processEvent(event1, smartHome);
        AlarmEvent event2 = new AlarmEvent(SensorEventType.ALARM_DEACTIVATE, 2345);
        alarmEventProcessor.processEvent(event2, smartHome);
        AlarmEvent event3 = new AlarmEvent(SensorEventType.ALARM_DEACTIVATE, 1234);
        alarmEventProcessor.processEvent(event3, smartHome);
        assertTrue(smartHome.getAlarm().isDeactivateState());
    }
}