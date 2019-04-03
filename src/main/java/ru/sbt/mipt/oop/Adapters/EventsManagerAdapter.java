package ru.sbt.mipt.oop.Adapters;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.processors.EventProcessor;

public class EventsManagerAdapter implements EventManager {
    private SensorEventsManager sensorEventsManager;

    public EventsManagerAdapter() {
        this.sensorEventsManager = new SensorEventsManager();
    }

    @Override
    public void Loop() {
        System.out.println("EventsManagerAdapter.Loop()");
        sensorEventsManager.start();
    }

    @Override
    public void addHomeEventsProcessor(EventProcessor eventProcessor, SmartHome smartHome) {
        System.out.println("handled");
        sensorEventsManager.registerEventHandler(new EventHandlerAdapter(eventProcessor, smartHome));
    }
}