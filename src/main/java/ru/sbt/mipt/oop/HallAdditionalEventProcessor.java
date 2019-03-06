package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class HallAdditionalEventProcessor extends DoorEventProcessor {
    @Override
    public void runEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.equalId(event)) {
                        if (door.equalName("hall")) {
                            smartHome.turnOffLight();
                        }

                    }

                }
            }
        }

    }
}
