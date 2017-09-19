package ru.nickly.bot.onenotemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {
    private String id;
    private String self;
    private String createdTime;
    private String title;
    private String lastModifiedTime;
}
