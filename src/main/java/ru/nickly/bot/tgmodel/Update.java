package ru.nickly.bot.tgmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Update {
    private Integer update_id;
    private Message message;
    private CallbackQuery callback_query;
}
