package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.Action;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        lights.forEach(light -> light.execute(action));
        doors.forEach(door -> door.execute(action));
    }


    @Override
    public String getComponentName() {
        return getClass().getName();
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public boolean equalName(String name) {
        return this.name.equals(name);
    }
}
