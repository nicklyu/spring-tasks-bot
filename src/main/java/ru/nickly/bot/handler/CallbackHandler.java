package ru.nickly.bot.handler;

import ru.nickly.bot.tgmodel.CallbackQuery;

import java.io.IOException;

public interface CallbackHandler {
    void handleCallback(CallbackQuery callback) throws IOException;
}
