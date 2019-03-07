package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    @Override
    public void runEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.equalId(event)) {
                        if (event.getType() == DOOR_OPEN) {
                            changeState(true, door, room, "was opened.");
                        } else {
                            changeState(true, door, room, "was closed.");
                        }
                    }
                }
            }
        }
    }
    private void changeState(boolean state, Door door, Room room, String action_str) {
        door.setOpen(state);
        StatePrinter statePrinter = new StatePrinter();
        statePrinter.sendMessage("Door " + door.getId() + " in room " + room.getName() + action_str);
    }

}
