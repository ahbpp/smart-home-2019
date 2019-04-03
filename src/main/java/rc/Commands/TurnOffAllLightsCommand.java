package rc.Commands;

import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOffAllLightsCommand implements Command {
    @Override
    public void execute(SmartHome smartHome) {
        smartHome.turnOffLight();
    }
}
