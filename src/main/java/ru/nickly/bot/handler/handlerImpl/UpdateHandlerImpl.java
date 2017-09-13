package ru.nickly.bot.handler.handlerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nickly.bot.handler.CallbackHandler;
import ru.nickly.bot.handler.MessageHandler;
import ru.nickly.bot.handler.UpdateHandler;
import ru.nickly.bot.tgmodel.Update;

import java.io.IOException;

@Component
public class UpdateHandlerImpl implements UpdateHandler {

    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private CallbackHandler callbackHandler;

    public void handleUpdate(Update update) throws IOException {
        if(update.getCallback_query()!=null)
            callbackHandler.handleCallback(update.getCallback_query());
        else
            messageHandler.handleMessage(update.getMessage());
    }
}
