package ru.nickly.bot.data.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nickly.bot.data.entity.Group;
import ru.nickly.bot.data.entity.Task;
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

    @Transactional
    @Override
    public Group addGroupTask(Integer id, Task task) {
        Group group = groupRepository.findOne(id);
        task.setGroup(group);
        group.getTasks().add(task);
        groupRepository.save(group);
        return group;
    }
}
