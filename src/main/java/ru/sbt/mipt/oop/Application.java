package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.processors.EventProcessor;
import ru.sbt.mipt.oop.processors.HallAdditionalEventProcessor;
import ru.sbt.mipt.oop.processors.LightsEventProcessor;
import ru.sbt.mipt.oop.readers.SmartHomeJsReader;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.eventsGetter.EventSensorGetter;

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

        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.processEvent(event, smartHome);
            }

            event = eventSensorGetter.getNextSensorEvent();
        }
    }

    private static ArrayList<EventProcessor> createProcessors() {
        ArrayList<EventProcessor> eventProcessors = new ArrayList<EventProcessor>();
        eventProcessors.add(new LightsEventProcessor());
        eventProcessors.add(new DoorEventProcessor());
        eventProcessors.add(new HallAdditionalEventProcessor());
        return  eventProcessors;
    }

}
