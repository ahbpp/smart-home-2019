package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.commands.StateMessagePrinter;

class AlertAlarmState implements AlarmState {

    private final int password;
    private final StateMessagePrinter stateMessagePrinter = new StateMessagePrinter();

    AlertAlarmState(int inputPassword) {
        this.password = inputPassword;
    }


    @Override
    public AlarmState activate(int inputPassword) {
        stateMessagePrinter.sendMessage("Alarm in alert state.");
        return this;
    }

    @Override
    public AlarmState deactivate(int inputPassword) {
        if (inputPassword == this.password){
            stateMessagePrinter.sendMessage("Alarm in deactivate state.");
            return new DeactivateAlarmState();
        } else {
            stateMessagePrinter.sendMessage("WrongPassword!");
            return this;
        }
    }

    @Override
    public AlarmState eventOccurred() {
        stateMessagePrinter.sendMessage("Alarm in alert state. Event occurred");
        return this;
    }
}
