package ru.nickly.bot.webservice;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import ru.nickly.bot.onenotemodel.GetOneNoteEntities;

@Service
public interface OneNoteApiService {

    @GET("v1.0/me/notes/notebooks")
    Call<GetOneNoteEntities> getNotebooks(@Header("Authorization") String authorization);

    @GET("v1.0/me/notes/notebooks/{notebookId}/sectiongroups")
    Call<GetOneNoteEntities> getSectionGroups(@Header("Authorization") String authorization,
                                              @Path("notebookId") String notebookId);

    @GET("v1.0/me/notes/sectionGroups/{sectionGroupId}/sections")
    Call<GetOneNoteEntities> getSections(@Header("Authorization") String authorization,
                                        @Path("sectionGroupId") String sectionGroupId);


    @GET("v1.0/me/notes/sections/{sectionId}/pages")
    Call<GetOneNoteEntities> getPages(@Header("Authorization") String authorization,
                                  @Path("sectionId") String sectionId);


    @GET("v1.0/me/notes/pages/{pageId}/content")
    Call<String> getContent(@Header("Authorization") String authorization,
                            @Path("pageId") String pageId);
}
