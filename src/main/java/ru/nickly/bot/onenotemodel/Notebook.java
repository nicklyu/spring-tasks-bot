package ru.nickly.bot.onenotemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Notebook {
    private String id;
    private String self;
    private String createdTime;
    private String name;
    private String createdBy;
    private String lastModifiedBy;
    private String lastModifiedTime;

}
