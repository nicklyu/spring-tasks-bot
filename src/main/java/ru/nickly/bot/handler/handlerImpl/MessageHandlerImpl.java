package ru.nickly.bot.handler.handlerImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ru.nickly.bot.data.entity.Group;
import ru.nickly.bot.data.entity.User;
import ru.nickly.bot.data.service.UserService;
import ru.nickly.bot.handler.MessageHandler;
import ru.nickly.bot.tgmodel.Answer;
import ru.nickly.bot.tgmodel.InlineKeyboardMarkup;
import ru.nickly.bot.tgmodel.Message;
import ru.nickly.bot.webservice.BotService;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class MessageHandlerImpl implements MessageHandler {

    private BotService botService;
    private UserService userService;

    public MessageHandlerImpl(BotService botService, UserService userService) {
        this.botService = botService;
        this.userService = userService;
    }

    public void handleMessage(Message message) throws IOException {
        Integer id = message.getChat().getId();
        String name = message.getFrom().getUsername();
        String text = message.getText();

        if (text.startsWith("/start")) {
            startCommand(id, name);
        } else if (text.startsWith("/addgroup")) {
            addGroupCommand(id);
        } else if (text.startsWith("/getgroups")) {
            getGroupsCommand(id);
        } else if (text.startsWith("/removegroup")) {
            removeGroupCommand(id);
        } else if (text.startsWith("/tasks")) {
            getTasksCommand(id);
        }
    }

    private void startCommand(Integer id, String name) {
        userService.addUser(User.builder().id(id).name(name).groups(new HashSet<Group>()).build());
    }

    private void addGroupCommand(Integer id) throws IOException {
        botService.sendMessage(Answer.builder().chat_id(id).text("Выберите группу которую хотите добавить:")
                .reply_markup(InlineKeyboardMarkup.create(userService.getGroupsByUserId(id), true)).build()).execute();
    }

    private void removeGroupCommand(Integer id) throws IOException {
        botService.sendMessage(Answer.builder().chat_id(id).text("Выберите группу которую хотите удалить:")
                .reply_markup(InlineKeyboardMarkup.create(userService.getGroupsByUserId(id), false)).build()).execute();

    }

    private void getTasksCommand(Integer id) {
        //TODO
        System.out.println("tasks shown");
    }

    private void getGroupsCommand(Integer id) throws IOException {
        StringBuffer answer = new StringBuffer("*Вы отслеживаете работы следующих групп:*\n");
        Set<Group> groups = userService.getGroupsByUserId(id);
        for(Group group:groups)
            answer.append(String.format("_Группа №%d_\n",group.getId()));
        botService.sendMessage(Answer.builder().chat_id(id).text(answer.toString()).parse_mode("Markdown").build()).execute();
    }

}
