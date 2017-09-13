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


    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User removeGroup(Integer userId, Integer groupId) {
        User user = userRepository.findOne(userId);
        user.getGroups().remove(Group.builder().id(groupId).build());
        userRepository.saveAndFlush(user);
        return user;
    }

    @Transactional
    public User addGroup(Integer userId, Integer groupId) {
        User user = userRepository.findOne(userId);
        user.getGroups().add(Group.builder().id(groupId).build());
        userRepository.saveAndFlush(user);
        return user;
    }

    @Transactional
    public Set<Group> getGroupsByUserId(Integer id) {
        return userRepository.findOne(id).getGroups();
    }
}
