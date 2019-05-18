package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControlRegistry;
import rc.RemoteController;
import ru.sbt.mipt.oop.readers.SmartHomeJsReader;

@Configuration
public class Config {

    @Bean
    SmartHomeJsReader smartHomeJsReader() {
        return new SmartHomeJsReader();
    }


    @Bean
    RemoteControlRegistry remoteControlRegistry() {return new RemoteControlRegistry();}

    @Bean
    RemoteController remoteControl() {
        return  new RemoteController();
    }

}