package ru.nickly.bot.tgmodel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InlineKeyboardButton {
    private String text;
    private String callback_data;
}
