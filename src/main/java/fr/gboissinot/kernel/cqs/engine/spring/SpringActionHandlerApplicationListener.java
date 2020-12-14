package fr.gboissinot.kernel.cqs.engine.spring;

import fr.gboissinot.kernel.cqs.engine.ActionMessageHandler;
import fr.gboissinot.kernel.cqs.engine.ActionMessageHandlersRegistry;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Map;

public class SpringActionHandlerApplicationListener implements ApplicationListener<ApplicationEvent> {

    private final ConfigurableListableBeanFactory beanFactory;
    private final ActionMessageHandlersRegistry handlersRegistry;

    public SpringActionHandlerApplicationListener(ConfigurableListableBeanFactory beanFactory, ActionMessageHandlersRegistry handlersRegistry) {
        this.beanFactory = beanFactory;
        this.handlersRegistry = handlersRegistry;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent contextRefreshedEvent) {
        registerCommandHandlers();
    }

    @SuppressWarnings("unchecked")
    private void registerCommandHandlers() {
        Map<String, ActionMessageHandler> actionMessageHandlerMap = beanFactory.getBeansOfType(ActionMessageHandler.class);
        for (ActionMessageHandler actionMessageHandler : actionMessageHandlerMap.values()) {
            handlersRegistry.subscribeHandler(actionMessageHandler.getClass());
        }
    }
}
