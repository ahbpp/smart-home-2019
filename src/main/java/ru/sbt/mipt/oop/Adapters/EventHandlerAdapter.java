package ru.sbt.mipt.oop.Adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.processors.EventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.util.HashMap;
import java.util.Map;

public class EventHandlerAdapter implements EventHandler {
    private EventProcessor eventProcessor;
    private SmartHome smartHome;

    // Available types :
    // "LightIsOn", "LightIsOff", "DoorIsOpen", "DoorIsClosed", "DoorIsLocked", "DoorIsUnlocked"

    private static Map<String, SensorEventType> sensorEventTypeMap = new HashMap<>();

    public EventHandlerAdapter(EventProcessor eventProcessor, SmartHome smartHome) {
        this.eventProcessor = eventProcessor;
        this.smartHome = smartHome;
        sensorEventTypeMap.put("LightIsOn", SensorEventType.LIGHT_ON);
        sensorEventTypeMap.put("LightIsOff", SensorEventType.LIGHT_OFF);
        sensorEventTypeMap.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        sensorEventTypeMap.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        sensorEventTypeMap.put("DoorIsLocked", SensorEventType.ALARM_ACTIVATE);
        sensorEventTypeMap.put("DoorIsUnlocked", SensorEventType.ALARM_DEACTIVATE);
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = new SensorEvent(sensorEventTypeMap.get(event.getEventType()),event.getObjectId());
        eventProcessor.processEvent(sensorEvent, smartHome);
    }
}
