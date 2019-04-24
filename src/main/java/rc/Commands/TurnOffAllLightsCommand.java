package rc.Commands;

import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOffAllLightsCommand implements Command {

    private final SmartHome smartHome;

    public TurnOffAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    @Override
    public void execute() {
        smartHome.turnOffLight();
    }
}
