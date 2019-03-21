package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.Alarm.Alarm;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.sensors.AlarmEvent;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

public class AlarmEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (!isAlarmEvent(event)) return;

        changeAlarmState(smartHome.getAlarm(), (AlarmEvent) event);

    }

    private void changeAlarmState(Alarm alarm, AlarmEvent event) {

        switch (event.getType()) {
            case ALARM_ACTIVATE:
                alarm.activate(event.getPassword());
                break;

            case ALARM_DEACTIVATE:
                alarm.deactivate(event.getPassword());
                break;

        }
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_ACTIVATE
                || event.getType() == SensorEventType.ALARM_DEACTIVATE;
    }

}
