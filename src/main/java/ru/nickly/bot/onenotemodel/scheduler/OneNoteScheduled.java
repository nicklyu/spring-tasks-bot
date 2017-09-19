package ru.nickly.bot.onenotemodel.scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.nickly.bot.onenotemodel.Authentication;
import ru.nickly.bot.onenotemodel.OneNoteEntity;
import ru.nickly.bot.onenotemodel.Token;
import ru.nickly.bot.webservice.OneNoteApiService;
import ru.nickly.bot.webservice.OneNoteAuthService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public class OneNoteScheduled {

    @Resource
    private Environment env;

    @Autowired
    private OneNoteApiService oneNoteApiService;

    @Autowired
    private OneNoteAuthService oneNoteAuthService;


    public void getOneNoteUpdates() throws IOException {
        if (Authentication.getInstance().getToken() != null){
            Authentication.getInstance().setToken(
                    oneNoteAuthService.refreshToken(env.getRequiredProperty("client.id"), env.getRequiredProperty("redirect.uri"),
                            env.getRequiredProperty("secret.code"), Authentication.getInstance().getToken().getRefresh_token(),
                            "refresh_token").execute().body()
            );
            List<OneNoteEntity> pages = oneNoteApiService.getPages(String.format("Bearer %s", Authentication.getInstance().getToken().getAccess_token()),
                    env.getRequiredProperty("section.id")).execute().body().getValue();
        }
    }
}
