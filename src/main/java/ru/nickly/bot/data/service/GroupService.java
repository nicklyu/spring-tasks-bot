package ru.nickly.bot.data.service;

import ru.nickly.bot.data.entity.Group;
import ru.nickly.bot.data.entity.User;

import java.util.List;

public interface GroupService {
    Group getGroupById(Integer id);
}
