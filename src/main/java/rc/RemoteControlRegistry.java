package rc;

import java.util.HashMap;
import java.util.Map;

public class RemoteControlRegistry {

    /**
     * Register remote control with id rcId.
     * When button on a real remote control device is pressed this library will call remoteControl.onButtonPressed(...).
     * @param remoteControl
     * @param rcId
     */

    private Map<String, RemoteControl> mapOfRemoteControls = new HashMap<>();

    public void registerRemoteControl(RemoteControl remoteControl, String rcId) {
        // here goes some library code which registers our remote control with given ID (rcId)
        mapOfRemoteControls.put(rcId, remoteControl);

    }


    public void pressedButtonOn(String rcId, String buttonCode) {
        RemoteControl remoteControl =  mapOfRemoteControls.get(rcId);

        if (remoteControl != null) {
            remoteControl.onButtonPressed(buttonCode);
        }
    }

}
