package ru.nickly.bot.data.service;

import ru.nickly.bot.data.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> findAllGroups();
}
