package rc.Commands;

import ru.sbt.mipt.oop.Alarm.Alarm;
import ru.sbt.mipt.oop.components.SmartHome;

public class ActivateAlarmCommand implements Command {
    @Override
    public void execute(SmartHome smartHome) {
        Alarm alarm = smartHome.getAlarm();
        alarm.activate();
    }
}
