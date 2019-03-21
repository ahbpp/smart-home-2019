package ru.sbt.mipt.oop.Alarm;

import ru.sbt.mipt.oop.commands.StateMessagePrinter;

class DeactivateAlarmState implements AlarmState {

    private final StateMessagePrinter stateMessagePrinter = new StateMessagePrinter();

    @Override
    public AlarmState activate(int inputPassword) {
        stateMessagePrinter.sendMessage("Alarm in activate state.");
        return new ActivateAlarmState(inputPassword);
    }

    @Override
    public AlarmState deactivate(int inputPassword) {
        System.out.println("Alarm has been already deactivated");
        return this;
    }

    @Override
    public AlarmState eventOccurred() {
        return this;
    }
}
