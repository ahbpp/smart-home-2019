package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.Alarm.Alarm;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

public class ProcessorAlarmDecorator implements EventProcessor{
    private EventProcessor eventProcessor;

    public ProcessorAlarmDecorator(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        Alarm alarm = smartHome.getAlarm();
        alarm.eventOccurred();
        if (alarm.isAlertState()) {
            return;
        }

        eventProcessor.processEvent(event, smartHome);
    }

}
