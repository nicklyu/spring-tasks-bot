package ru.nickly.bot.data.service;

import ru.nickly.bot.data.entity.Group;
import ru.nickly.bot.data.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User addUser(User user);
    User addGroup(Integer userId, Integer groupId);
    User removeGroup(Integer userId, Integer groupId);
    Set<Group> getGroupsByUserId(Integer id);
    List<User> findAllUsers();
}
