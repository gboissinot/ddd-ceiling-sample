package fr.gboissinot.kernel.cqs.engine.spring;

import fr.gboissinot.kernel.cqs.engine.ActionMessage;
import fr.gboissinot.kernel.cqs.engine.ActionMessageHandler;
import fr.gboissinot.kernel.cqs.engine.ActionMessageHandlerResolver;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.GenericTypeResolver;

public class SpringActionMessageHandlerResolver implements ActionMessageHandlerResolver {

    private final BeanFactory beanFactory;

    public SpringActionMessageHandlerResolver(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends ActionMessage> resolveActionMessageType(Class<? extends ActionMessageHandler> actionMessageHandlerClass) {
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(actionMessageHandlerClass, ActionMessageHandler.class);
        if (generics != null && generics.length >= 2) {
            return (Class<? extends ActionMessage>) generics[1];
        }
        throw new IllegalStateException(String.format("Action Message Handler '%s' must have an associated action message", actionMessageHandlerClass));
    }

    @Override
    public <R, M extends ActionMessage, H extends ActionMessageHandler<R, M>> H getActionMessageHandler(Class<? extends H> messageHandlerClass) {
        return beanFactory.getBean(messageHandlerClass);
    }
}
