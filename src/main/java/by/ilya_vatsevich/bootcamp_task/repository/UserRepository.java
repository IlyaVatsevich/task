package by.ilya_vatsevich.bootcamp_task.repository;

import by.ilya_vatsevich.bootcamp_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {}
