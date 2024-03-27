package org.example.services;

import org.example.entities.User;

import java.util.List;

public interface userService {
    boolean save(User user);
    boolean isExist(String id);
    boolean isExist(User user);
    boolean changeUser(User user);
    User getUser(String name);
    boolean deleteUser(User user);
}
