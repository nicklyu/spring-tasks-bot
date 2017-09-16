package ru.nickly.bot.tgmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageReplyMarkup {
    private Integer chat_id;
    private Integer message_id;
    private InlineKeyboardMarkup reply_markup;
}
