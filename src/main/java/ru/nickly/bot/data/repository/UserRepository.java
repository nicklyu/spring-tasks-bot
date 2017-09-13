package ru.nickly.bot.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nickly.bot.data.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
