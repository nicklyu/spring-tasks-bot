package ru.nickly.bot.webservice;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import ru.nickly.bot.onenotemodel.GetNotebooksResult;
import ru.nickly.bot.onenotemodel.GetSectionsResult;

@Service
public interface OneNoteApiService {

    @GET("v1.0/me/notes/notebooks")
    Call<GetNotebooksResult> getNotebooks(@Header("Authorization") String authorization);

    @GET("v1.0/me/notes/notebooks/{notebookId}/sections")
    Call<GetSectionsResult> getSections(@Header("Authorization") String authorization,
                                        @Path("notebookId") String notebookId);

}
