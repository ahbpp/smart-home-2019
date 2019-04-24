package rc.Commands;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

public class CloseHallDoorCommand implements Command {

    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        for (Room room : smartHome.getRooms()) {
            if (room.equalName("hall")) {
                room.execute(actionable -> {
                    if (actionable.getComponentName().equals("ru.sbt.mipt.oop.components.Door")){
                        Door door = (Door) actionable;
                        door.close();
                    }
                });
            }
        }

    }
}
