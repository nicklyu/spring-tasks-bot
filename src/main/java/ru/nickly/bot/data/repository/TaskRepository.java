package ru.nickly.bot.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nickly.bot.data.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
