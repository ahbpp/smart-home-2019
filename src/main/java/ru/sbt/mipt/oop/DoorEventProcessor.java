package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (!isDoorEvent(event)) {return;}
        smartHome.execute(actionable -> {
            if (actionable.getComponentName().equals("ru.sbt.mipt.oop.Door")){
            Door door = (Door) actionable;
            if (door.getId().equals(event.getObjectId())) {
                changeState(door, event);
            }
        }
        });
        
    }
    private void changeState(Door door, SensorEvent event) {
        if (event.getType() == DOOR_OPEN){
            door.setOpen(true);
            StateMessagePrinter statePrinter = new StateMessagePrinter();
            statePrinter.sendMessage("Door " + door.getId() + " was opened");
        } else {
            door.setOpen(false);
            StateMessagePrinter statePrinter = new StateMessagePrinter();
            statePrinter.sendMessage("Door " + door.getId() + " was closed");
        }
    }

    private boolean isDoorEvent(SensorEvent event){
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
    }


}
