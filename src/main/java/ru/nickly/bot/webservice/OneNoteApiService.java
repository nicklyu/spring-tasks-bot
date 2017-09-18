package ru.nickly.bot.webservice;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import ru.nickly.bot.onenotemodel.GetNotebooksResult;

@Service
public interface OneNoteApiService {

    @GET("v1.0/me/notes/notebooks")
    Call<GetNotebooksResult> getNotebooks(@Header("Authorization") String authorization);
}
