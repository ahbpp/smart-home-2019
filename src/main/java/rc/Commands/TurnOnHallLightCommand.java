package rc.Commands;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOnHallLightCommand implements Command{
    @Override
    public void execute(SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            if (room.equalName("hall")) {
                room.execute(actionable -> {
                    if (actionable.getComponentName().equals("ru.sbt.mipt.oop.components.Light")){
                        Light light = (Light) actionable;
                        light.turnOn();
                    }
                });
            }
        }
    }
}
