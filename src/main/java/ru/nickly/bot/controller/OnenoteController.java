package ru.nickly.bot.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import retrofit2.Response;
import ru.nickly.bot.onenotemodel.Authentication;
import ru.nickly.bot.onenotemodel.Notebook;
import ru.nickly.bot.onenotemodel.Token;
import ru.nickly.bot.webservice.OneNoteApiService;
import ru.nickly.bot.webservice.OneNoteAuthService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
public class OnenoteController {

    @Resource
    private Environment env;

    private OneNoteAuthService oneNoteAuthService;

    private OneNoteApiService oneNoteApiService;

    public OnenoteController(OneNoteAuthService oneNoteAuthService, OneNoteApiService oneNoteApiService) {
        this.oneNoteAuthService = oneNoteAuthService;
        this.oneNoteApiService = oneNoteApiService;
    }


    @RequestMapping(value = "/auth", params = {"code"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String setCode(@RequestParam("code") String code) throws IOException {
        Authentication.getInstance().setCode(code);
        Response response =
                oneNoteAuthService.getToken(env.getRequiredProperty("client.id"), env.getRequiredProperty("redirect.uri"),
                        env.getRequiredProperty("secret.code"), Authentication.getInstance().getCode(),
                        env.getRequiredProperty("auth.grant.type"))
                        .execute();
        if (response.body() != null) {
            Authentication.getInstance().setToken((Token) response.body());
            return "Token written";
        } else
            return response.errorBody().string();
    }


    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public ModelAndView start() throws IOException {
        String uri = String.format("https://login.live.com/oauth20_authorize.srf?client_id=%s&scope=%s&response_type=%s&redirect_uri=%s",
                env.getRequiredProperty("client.id"), env.getRequiredProperty("scope"), "code", env.getRequiredProperty("redirect.uri"));

        return new ModelAndView("redirect:" + uri);
    }
}
