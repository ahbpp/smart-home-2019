package rc;

import org.junit.Before;
import org.junit.Test;
import rc.Commands.*;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.processors.HallAdditionalEventProcessor;
import ru.sbt.mipt.oop.readers.SmartHomeJsReader;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RemoteControllerTest {

    private SmartHome smartHome;
    private RemoteController remoteController;
    HallAdditionalEventProcessor hallAdditionalEventProcessor;

    @Before
    public void init() throws IOException {
        SmartHomeJsReader smartHomeJsReader = new SmartHomeJsReader();
        smartHome = smartHomeJsReader.readSmartHome();
        remoteController = new RemoteController(smartHome);
        hallAdditionalEventProcessor = new HallAdditionalEventProcessor();

        remoteController.setButton("A", new ActivateAlarmCommand(smartHome.getAlarm()));
        remoteController.setButton("B", new AlertAlarmCoomand(smartHome.getAlarm()));
        remoteController.setButton("C", new CloseHallDoorCommand(smartHome));
        remoteController.setButton("D", new TurnOffAllLightsCommand(smartHome));
        remoteController.setButton("1", new TurnOnAllLightsCommand(smartHome));
        remoteController.setButton("2", new TurnOnHallLightCommand(smartHome));

    }

    @Test
    public void ButtonPressedA() {
        smartHome.getAlarm().setPassword(1234);
        remoteController.onButtonPressed("A");
        assertTrue(smartHome.getAlarm().isActivateState());
    }

    @Test
    public void ButtonPressedB() {
        smartHome.getAlarm().setPassword(1234);
        remoteController.onButtonPressed("B");
        assertTrue(smartHome.getAlarm().isAlertState());
        smartHome.getAlarm().deactivate(1234);
    }

    @Test
    public void ButtonPressedC() {
        SensorEvent event1 = new SensorEvent(SensorEventType.DOOR_OPEN, "4");
        hallAdditionalEventProcessor.processEvent(event1, smartHome);
        remoteController.onButtonPressed("C");
        Door door = smartHome.getDoorByld("4");
        assertEquals(false, door.getState());
    }

    @Test
    public void ButtonPressedD() {
        remoteController.onButtonPressed("D");
        ArrayList<Light> lights = smartHome.getLights();
        for (Light light : lights) {
            assertEquals(false, light.getState());
        }
    }

    @Test
    public void ButtonPressed1() {
        remoteController.onButtonPressed("1");
        ArrayList<Light> lights = smartHome.getLights();
        for (Light light : lights) {
            assertEquals(true, light.getState());
        }
    }

    @Test
    public void ButtonPressed2() {
        remoteController.onButtonPressed("2");
        for (Room room : smartHome.getRooms()) {
            if (room.equalName("hall")) {
                for (Light light : room.getLights()) {
                    assertEquals(true, light.getState());
                }
            }
        }
    }
}