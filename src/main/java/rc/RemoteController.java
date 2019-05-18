package rc;

import rc.Commands.Command;

import java.util.HashMap;
import java.util.Map;

public class RemoteController implements RemoteControl {



    private Map<String, Command> mapRemoteControl = new HashMap<>(8);


    @Override
    public void onButtonPressed(String buttonCode) {
        Command command = mapRemoteControl.get(buttonCode);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("This button has no command");
        }
    }

    public void setButton(String buttonCode, Command command) {
        mapRemoteControl.put(buttonCode, command);
    }

}
