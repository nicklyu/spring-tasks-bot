package ru.nickly.bot.webservice;

import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.nickly.bot.tgmodel.Answer;
import ru.nickly.bot.tgmodel.AnswerCallbackQuery;
import ru.nickly.bot.tgmodel.MessageReplyMarkup;


@Service
public interface BotService {
    @POST("sendMessage")
    Call<ResponseBody> sendMessage(@Body Answer answer);

    @POST("answerCallbackQuery")
    Call<ResponseBody> answerCallbackQuery(@Body AnswerCallbackQuery answerCallbackQuery);

    @POST("editMessageReplyMarkup")
    Call<ResponseBody> editMessageReplyMarkup(@Body MessageReplyMarkup messageReplyMarkup);
}
