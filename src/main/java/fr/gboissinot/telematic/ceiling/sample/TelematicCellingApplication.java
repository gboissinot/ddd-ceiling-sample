package fr.gboissinot.telematic.ceiling.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelematicCellingApplication {

    @Autowired
    private static ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {
//        SpringApplication sa = new SpringApplication();
//        sa.addListeners(
//                new SpringActionHandlerApplicationListener(
//                        beanFactory,
//                        ActionMessageHandlersRegistry.valueOf(
//                                new SpringActionMessageHandlerResolver(beanFactory))));
//        sa.run(TelematicCellingApplication.class, args);

        SpringApplication.run(TelematicCellingApplication.class, args);
    }
}