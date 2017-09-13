package ru.nickly.bot.tgmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackQuery {
    private String id;
    private User from;
    private Message message;
    private String data;
}
