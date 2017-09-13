package ru.nickly.bot.data.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nickly.bot.data.entity.Group;
import ru.nickly.bot.data.repository.GroupRepository;
import ru.nickly.bot.data.service.GroupService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupRepository groupRepository;

    @Transactional
    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }
}
