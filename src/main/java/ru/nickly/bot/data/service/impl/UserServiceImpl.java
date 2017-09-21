package ru.nickly.bot.data.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nickly.bot.data.entity.Group;
import ru.nickly.bot.data.entity.User;
import ru.nickly.bot.data.repository.UserRepository;
import ru.nickly.bot.data.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User addGroup(Integer userId, Group group) {
        User user = userRepository.findOne(userId);
        user.getGroups().add(group);
        userRepository.save(user);
        return user;
    }

    @Override
    public User removeGroup(Integer userId, Group group) {
        User user = userRepository.findOne(userId);
        user.getGroups().remove(group);
        userRepository.save(user);
        return user;
    }

    @Override
    public Set<Group> getGroupsByUserId(Integer id) {
        return userRepository.findOne(id).getGroups();
    }
}
