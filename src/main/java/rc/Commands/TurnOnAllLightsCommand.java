package rc.Commands;

import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOnAllLightsCommand implements Command {

    private final SmartHome smartHome;

    public TurnOnAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.turnOnLight();
    }
}
