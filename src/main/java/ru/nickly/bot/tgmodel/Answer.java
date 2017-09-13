package ru.nickly.bot.tgmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer {
    private Integer chat_id;
    private String text;
    private String parse_mode;
    private InlineKeyboardMarkup reply_markup;
}
