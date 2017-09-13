package ru.nickly.bot.tgmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chat {
    private Integer id;
    private String type;
    private String title;
    private String username;
    private String first_name;
    private String last_name;
    private String description;
    private String invite_link;
}
