package ru.nickly.bot.tgmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private Integer id;
    private Boolean is_bot;
    private String first_name;
    private String last_name;
    private String username;
    private String language_code;
}
