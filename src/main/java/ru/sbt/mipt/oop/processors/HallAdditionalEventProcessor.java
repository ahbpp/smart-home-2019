package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;

public class HallAdditionalEventProcessor extends DoorEventProcessor {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() != DOOR_CLOSED) {return;}
        // событие от двери
        for (Room room : smartHome.getRooms()) {
            if (room.equalName("hall")) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                       smartHome.turnOffLight();
                    }
                }
            }
        }
    }
}
