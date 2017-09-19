package ru.nickly.bot.onenotemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class OneNoteEntity {
    private String id;
    private String self;
    private String createdTime;
    private String name;
    private String title;
    private String createdBy;
    private String lastModifiedBy;
    private String lastModifiedTime;
}
