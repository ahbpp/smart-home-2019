package rc.Commands;

import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOnAllLightsCommand implements Command {

    SmartHome smartHome;

    @Override
    public void execute(SmartHome smartHome) {
        smartHome.turnOnLight();
    }
}
