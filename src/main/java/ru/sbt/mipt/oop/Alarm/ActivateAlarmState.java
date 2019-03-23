package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.commands.StateMessagePrinter;

class ActivateAlarmState implements AlarmState {

    private final int password;
    private final StateMessagePrinter stateMessagePrinter = new StateMessagePrinter();

    ActivateAlarmState(int inputPassword) {
        this.password = inputPassword;
    }

    @Override
    public AlarmState activate(int inputPassword) {
        System.out.println("Alarm has been already activated");
        return this;

    }

    @Override
    public AlarmState deactivate(int inputPassword) {
        if (inputPassword == this.password){
            stateMessagePrinter.sendMessage("Alarm in deactivate state.");
            return new DeactivateAlarmState();
        } else {
            stateMessagePrinter.sendMessage("Alarm in alert state. Wrong password");
            return new AlertAlarmState(this.password);
        }
    }

    @Override
    public AlarmState eventOccurred() {
        stateMessagePrinter.sendMessage("Alarm in alert state. Event Occurred");
        return new AlertAlarmState(this.password);
    }
}
