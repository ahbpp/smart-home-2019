package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Alarm.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Room> rooms;
    private Alarm alarm;

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
        alarm = new Alarm();
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

    public Door getDoorByld(String id) {
        for (Room room : this.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id)) {
                    return door;
                }
            }
        }
        return null;
    }

    public Light getLightByld(String id) {
        for (Room room : this.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(id)) {
                    return light;
                }
            }
        }
        return null;
    }

    public ArrayList<Door> getDoors() {
        ArrayList<Door> doors = new ArrayList<Door>();
        for (Room room : this.getRooms()) {
            doors.addAll(room.getDoors());
        }
        return doors;
    }

    public ArrayList<Light> getLights() {
        ArrayList<Light> lights = new ArrayList<Light>();
        for (Room room : this.getRooms()) {
            lights.addAll(room.getLights());
        }
        return lights;
    }

    public Alarm getAlarm() {
        return this.alarm;
    }
}
