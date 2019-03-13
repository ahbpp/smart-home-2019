package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.Action;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Room> rooms;

    @Override
    public void execute(Action action) {
        action.execute(this);
        rooms.forEach(room -> room.execute(action));

    }


    @Override
    public String getComponentName() {
        return getClass().getName();
    }

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void turnOffLight() {
        this.execute(actionable -> {
            if (actionable.getComponentName().equals("ru.sbt.mipt.oop.components.Light")){
                Light light = (Light) actionable;
                light.turnOff();
            }
        });
    }
}
