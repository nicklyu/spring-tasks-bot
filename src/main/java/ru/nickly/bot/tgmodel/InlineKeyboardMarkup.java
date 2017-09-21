package ru.nickly.bot.tgmodel;

import lombok.Builder;
import lombok.Data;
import ru.nickly.bot.data.entity.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class InlineKeyboardMarkup {
    private List<List<InlineKeyboardButton>> inline_keyboard;

    public static InlineKeyboardMarkup create(Set<Group> groups, Boolean toAdd) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> level = new ArrayList<>();
        int index = 0;
        for (int i = 171; i <= 177; i++) {
            if (groups.contains(Group.builder().id(i).build()) != toAdd) {
                level.add(InlineKeyboardButton.builder().text(Integer.toString(i)).callback_data((toAdd ? "add" : "del") + i).build());
                index++;
            }
            if (index % 3 == 0) {
                index = 0;
                buttons.add(level);
                level = new ArrayList<>();
            }
        }
        buttons.add(level);
        return InlineKeyboardMarkup.builder().inline_keyboard(buttons).build();

    }
}
