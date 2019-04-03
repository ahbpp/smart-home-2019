package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.commands.StateMessagePrinter;

public class Alarm{

    private AlarmState alarmState;
    private int password = -1;
    private final StateMessagePrinter stateMessagePrinter = new StateMessagePrinter();

    public Alarm() {
        this.alarmState = new DeactivateAlarmState();
    }

    public Alarm(int inputPassword) {

        this.alarmState = new DeactivateAlarmState();
        this.password = inputPassword;
    }

    public void activate(int inputPassword) {

        alarmState = alarmState.activate(inputPassword);
        this.password = inputPassword;
    }

    public void activate() {
        if (password == -1) {
            stateMessagePrinter.sendMessage("There isn't password");
            return;
        }
        alarmState = alarmState.activate(password);
    }

    public void alert() {
        alarmState = new AlertAlarmState(password);
        stateMessagePrinter.sendMessage("Alarm in alert state.");
    }

    public void setPassword(int newPassword) {
        if (this.password == -1) {
            this.password = newPassword;
        } else {
            stateMessagePrinter.sendMessage("There is password now! You need change password.");
        }
    }

    public void changePassword(int oldPassword, int newPassword) {
        if (isDeactivateState()) {
            stateMessagePrinter.sendMessage("Alarm in activate state");
            return;
        }

        if (oldPassword != this.password) {
            stateMessagePrinter.sendMessage("WrongPassword!");
            return;
        }

        this.password = newPassword;
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
