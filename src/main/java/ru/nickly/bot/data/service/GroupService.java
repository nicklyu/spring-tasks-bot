package ru.nickly.bot.data.service;

import ru.nickly.bot.data.entity.Group;
import ru.nickly.bot.data.entity.Task;

public interface GroupService {
    Group getGroupById(Integer id);
    Group addGroupTask(Integer id, Task task);
}
