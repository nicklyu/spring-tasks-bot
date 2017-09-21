package ru.nickly.bot.handler.handlerImpl;

import org.springframework.stereotype.Component;
import ru.nickly.bot.data.service.GroupService;
import ru.nickly.bot.data.service.UserService;
import ru.nickly.bot.handler.CallbackHandler;
import ru.nickly.bot.tgmodel.AnswerCallbackQuery;
import ru.nickly.bot.tgmodel.CallbackQuery;
import ru.nickly.bot.tgmodel.InlineKeyboardMarkup;
import ru.nickly.bot.tgmodel.MessageReplyMarkup;
import ru.nickly.bot.webservice.BotService;

import java.io.IOException;

@Component
public class CallbackHandlerImpl implements CallbackHandler {

    private UserService userService;
    private BotService botService;
    private GroupService groupService;

    public CallbackHandlerImpl(UserService userService, BotService botService, GroupService groupService) {
        this.userService = userService;
        this.botService = botService;
        this.groupService = groupService;
    }

    public void handleCallback(CallbackQuery callback) throws IOException {
        Integer id = callback.getFrom().getId();
        String text = callback.getData();
        String queryId = callback.getId();
        Integer messageId = callback.getMessage().getMessage_id();

        if (text.startsWith("add")) {
            addGroupCallback(id, text, queryId, messageId);
        } else if (text.startsWith("del")) {
            removeGroupCallback(id, text, queryId, messageId);
        }
    }

    private void addGroupCallback(Integer id, String command, String queryId, Integer messageId) throws IOException {
        Integer groupId = Integer.parseInt(command.substring(3));
        userService.addGroup(id, groupService.getGroupById(groupId));

        botService.editMessageReplyMarkup(MessageReplyMarkup.builder().chat_id(id)
            .message_id(messageId).reply_markup(InlineKeyboardMarkup.create(userService.getGroupsByUserId(id), true))
                .build()).execute();

        botService.answerCallbackQuery(AnswerCallbackQuery.builder().callback_query_id(queryId)
                .text("Вы теперь отслеживаете группу " + groupId).build()).execute();
    }

    private void removeGroupCallback(Integer id, String command, String queryId, Integer messageId) throws IOException {
        Integer groupId = Integer.parseInt(command.substring(3));
        userService.removeGroup(id, groupService.getGroupById(groupId));

        botService.editMessageReplyMarkup(MessageReplyMarkup.builder().chat_id(id)
                .message_id(messageId).reply_markup(InlineKeyboardMarkup.create(userService.getGroupsByUserId(id), false))
                .build()).execute();

        botService.answerCallbackQuery(AnswerCallbackQuery.builder().callback_query_id(queryId)
                .text("Вы теперь не отслеживаете группу " + groupId).build()).execute();
    }
}
