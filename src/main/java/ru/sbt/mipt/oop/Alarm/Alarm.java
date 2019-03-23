package ru.sbt.mipt.oop.Alarm;

public class Alarm{

    AlarmState alarmState;

    public Alarm() {
        this.alarmState = new DeactivateAlarmState();
    }

    public void activate(int inputPassword) {
        alarmState = alarmState.activate(inputPassword);
    }

    public void deactivate(int inputPassword) {
        alarmState = alarmState.deactivate(inputPassword);
    }

    public void eventOccurred() {
        this.alarmState = alarmState.eventOccurred();
    }

    public boolean isAlertState() {
        return this.alarmState instanceof AlertAlarmState;
    }

    public boolean isActivateState() {
        return this.alarmState instanceof ActivateAlarmState;
    }

    public boolean isDeactivateState() {
        return this.alarmState instanceof DeactivateAlarmState;
    }
}
