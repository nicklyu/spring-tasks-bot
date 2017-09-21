package ru.nickly.bot.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nickly.bot.handler.UpdateHandler;
import ru.nickly.bot.tgmodel.Update;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class WebhookController {

    @Resource
    private Environment env;

    private UpdateHandler handler;


    public WebhookController(UpdateHandler handler) {
        this.handler = handler;
    }

    @RequestMapping(value = "/webhook/{token}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void getUpdates(@PathVariable String token, @RequestBody Update update) throws IOException {
        if (env.getRequiredProperty("app.token").equalsIgnoreCase(token))
            handler.handleUpdate(update);
    }


}
