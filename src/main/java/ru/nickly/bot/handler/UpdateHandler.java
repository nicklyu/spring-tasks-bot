package ru.nickly.bot.handler;

import ru.nickly.bot.tgmodel.Update;

import java.io.IOException;

public interface UpdateHandler {
    void handleUpdate(Update update) throws IOException;
}
