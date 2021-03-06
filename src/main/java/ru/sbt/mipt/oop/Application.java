package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Adapters.EventsManagerAdapter;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventsGetter.EventSensorGetter;
import ru.sbt.mipt.oop.processors.*;
import ru.sbt.mipt.oop.readers.SmartHomeJsReader;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.io.IOException;
import java.util.ArrayList;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHomeJsReader smartHomeJsReader = new SmartHomeJsReader();
        SmartHome smartHome = smartHomeJsReader.readSmartHome();
        runEvents(smartHome);

    }

    private static void runEvents(SmartHome smartHome) {
        EventSensorGetter eventSensorGetter = new EventSensorGetter();
        SensorEvent event = eventSensorGetter.getNextSensorEvent();
        ArrayList<EventProcessor> eventProcessors = createProcessors();
        EventsManagerAdapter eventsManagerAdapter = new EventsManagerAdapter();
        for (EventProcessor eventProcessor : eventProcessors) {
            eventsManagerAdapter.addHomeEventsProcessor(eventProcessor, smartHome);
        }
        eventsManagerAdapter.Loop();
    }

    private static ArrayList<EventProcessor> createProcessors() {
        ArrayList<EventProcessor> eventProcessors = new ArrayList<EventProcessor>();
        eventProcessors.add(new ProcessorAlarmDecorator(new LightsEventProcessor()));
        eventProcessors.add(new ProcessorAlarmDecorator(new DoorEventProcessor()));
        eventProcessors.add(new ProcessorAlarmDecorator(new HallAdditionalEventProcessor()));
        eventProcessors.add(new AlarmEventProcessor());
        return  eventProcessors;
    }

}
