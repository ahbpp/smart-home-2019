package rc.Commands;

import ru.sbt.mipt.oop.Alarm.Alarm;

public class AlertAlarmCoomand implements Command {

    private final Alarm alarm;

    public  AlertAlarmCoomand(Alarm alarm) {
        this.alarm = alarm;
    }


    @Override
    public void execute() {
        alarm.alert();
    }
}
