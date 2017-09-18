package ru.nickly.bot.webservice;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.http.*;
import ru.nickly.bot.onenotemodel.Token;

@Service
public interface OneNoteService {

    @FormUrlEncoded
    @POST("oauth20_token.srf")
    Call<Token> getToken(@Field("client_id") String clientId, @Field("redirect_uri") String redirectedUri,
                                @Field("client_secret") String clientSecret, @Field("code") String code,
                                @Field("grant_type") String grantType);



    @FormUrlEncoded
    @POST("oauth20_token.srf")
    Call<Token> refreshToken(@Field("client_id") String clientId, @Field("redirect_uri") String redirectUri,
                             @Field("client_secret") String clientSecret, @Field("refresh_token") String refreshToken,
                             @Field("grant_type") String grantType);

}
