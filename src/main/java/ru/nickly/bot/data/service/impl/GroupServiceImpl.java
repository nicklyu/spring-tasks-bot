package ru.nickly.bot.data.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nickly.bot.data.entity.Group;
import ru.nickly.bot.data.repository.GroupRepository;
import ru.nickly.bot.data.service.GroupService;

import javax.annotation.Resource;


@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupRepository groupRepository;

    @Transactional
    @Override
    public Group getGroupById(Integer id) {
        return groupRepository.findOne(id);
    }
}
