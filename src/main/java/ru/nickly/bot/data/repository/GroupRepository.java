package ru.nickly.bot.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nickly.bot.data.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
