package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightsEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SensorEvent event, SmartHome smartHome) {
        if (!isLightEvent(event)){return;}
            // событие от источника света
        smartHome.execute(actionable -> {
            if (actionable.getComponentName().equals("ru.sbt.mipt.oop.Light")){
            Light light = (Light) actionable;
            if (light.getId().equals(event.getObjectId())) {
                changeState(light, event);
            }
        }
        });
    }

    private boolean isLightEvent(SensorEvent event) {
        return (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }

    private void changeState(Light light, SensorEvent event) {
        if (event.getType() == LIGHT_ON) {
            light.setOn(true);
            StateMessagePrinter statePrinter = new StateMessagePrinter();
            statePrinter.sendMessage("Light " + light.getId() + " was turned on.");
        } else {
            light.setOn(false);
            StateMessagePrinter statePrinter = new StateMessagePrinter();
            statePrinter.sendMessage("Light " + light.getId() + " was turned off.");
        }
    }
}
