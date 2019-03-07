package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.ArrayList;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHomeReader smartHomeReader = new SmartHomeReader();
        SmartHome smartHome = smartHomeReader.ReadSmartHome();
        runEvents(smartHome);

    }

    private static void runEvents(SmartHome smartHome) {
        SensorEvent event = EventSensorGetter.getNextSensorEvent();
        ArrayList<EventProcessor> eventProcessors = createProcessors();

        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.runEvent(event, smartHome);
            }

            event = EventSensorGetter.getNextSensorEvent();
        }
    }

    private static ArrayList<EventProcessor> createProcessors() {
        ArrayList<EventProcessor> eventProcessors = new ArrayList<EventProcessor>();
        eventProcessors.add(new LightsEventProcessor());
        eventProcessors.add(new DoorEventProcessor());
        eventProcessors.add(new HallAdditigitonalEventProcessor());
        return  eventProcessors;
    }

}
