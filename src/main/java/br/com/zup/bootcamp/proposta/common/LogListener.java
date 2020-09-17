package br.com.zup.bootcamp.proposta.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class LogListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    @EventListener
    void onEventOccur(InternalEvent event) {

        if (event.isSuccess()) {
            if (logger.isInfoEnabled()) logger.info(event.toJson());
        } else if (event.getException() instanceof DomainException) {
            if (logger.isWarnEnabled()) logger.warn(event.toJson(), event.getException());
        } else {
            if (logger.isErrorEnabled()) logger.error(event.toJson(), event.getException());
        }
    }
}
