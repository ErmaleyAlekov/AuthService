package org.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.entities.User;
public interface usersRepository extends JpaRepository<User, String> {
}
