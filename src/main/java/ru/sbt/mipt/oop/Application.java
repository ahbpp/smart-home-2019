package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHomeReader smartHomeReader = new SmartHomeReader();
        SmartHome smartHome = smartHomeReader.ReadSmartHome();
        runEvents(smartHome);

    }

    private static void runEvents(SmartHome smartHome) {
        SensorEvent event = EventSesorGetter.getNextSensorEvent();
        ArrayList<EventProcessor> eventProcessors = new ArrayList<EventProcessor>();
        eventProcessors.add(new LightsEventProcessor());
        eventProcessors.add(new DoorEventProcessor());

        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.runEvent(event, smartHome);
            }

            event = EventSesorGetter.getNextSensorEvent();
        }
    }

}
