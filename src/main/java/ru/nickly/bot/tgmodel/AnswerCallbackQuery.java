package ru.nickly.bot.tgmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerCallbackQuery {
    private String callback_query_id;
    private String text;
    private Boolean show_alert;
    private String url;
}
