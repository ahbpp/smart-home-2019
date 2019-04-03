package rc;

import rc.Commands.Command;
import ru.sbt.mipt.oop.components.SmartHome;

import java.util.HashMap;
import java.util.Map;

public class RemoteController implements RemoteControl {



    private Map<String, Command> mapRemoteControl = new HashMap<>(8);
    SmartHome smartHome;

    public RemoteController(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        Command command = mapRemoteControl.get(buttonCode);
        if (command != null) {
            command.execute(smartHome);
        } else {
            System.out.println("This button has no command");
        }
    }

    public void setButton(String buttonCode, Command command) {
        mapRemoteControl.put(buttonCode, command);
    }

}
