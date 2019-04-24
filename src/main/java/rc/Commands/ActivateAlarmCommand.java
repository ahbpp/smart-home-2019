package rc.Commands;

import ru.sbt.mipt.oop.Alarm.Alarm;

public class ActivateAlarmCommand implements Command {
    private final Alarm alarm;
    private int password = -1;


    public  ActivateAlarmCommand(Alarm alarm, int inputPassword) {
        this.alarm = alarm;
        this.password = inputPassword;
    }

    public ActivateAlarmCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        if (password != -1) {
            alarm.activate(password);
        } else {
            alarm.activate();
        }
    }
}
