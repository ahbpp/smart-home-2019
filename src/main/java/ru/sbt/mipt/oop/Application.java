package ru.sbt.mipt.oop;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
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
        Logger logger = LogManager.getLogger(Application.class);
        // считываем состояние дома из файла
        logger.info("Starting configuration");
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        SmartHomeJsReader smartHomeReader = context.getBean(SmartHomeJsReader.class);
        SmartHome smartHome = smartHomeReader.readSmartHome();


        RemoteControlRegistry remoteControlRegistry = context.getBean(RemoteControlRegistry.class);
        RemoteControl smartHomeRemoteControl = context.getBean(RemoteControl.class);
        remoteControlRegistry.registerRemoteControl(smartHomeRemoteControl, "1");

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
