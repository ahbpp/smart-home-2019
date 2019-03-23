package ru.sbt.mipt.oop.Alarm;

interface AlarmState {

    AlarmState activate (int inputPassword);
    AlarmState deactivate (int inputPassword);
    AlarmState eventOccurred();
}
