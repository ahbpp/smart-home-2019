package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightsEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            // событие от источника света
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            changeState(true, light, room, "was turned on.");
                        } else {
                            changeState(false, light, room, "was turned off.");
                        }
                    }
                }
            }
        }
    }

    private void changeState(boolean state, Light light, Room room, String action_str) {
        light.setOn(state);
        StateMessagePrinter statePrinter = new StateMessagePrinter();
        statePrinter.sendMessage("Light " + light.getId() + " in room " + room.getName() + action_str);
    }
}
