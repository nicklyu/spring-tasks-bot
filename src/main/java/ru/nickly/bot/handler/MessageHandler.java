package ru.nickly.bot.handler;

import ru.nickly.bot.tgmodel.Message;

import java.io.IOException;

public interface MessageHandler {
    void handleMessage(Message message) throws IOException;
}
