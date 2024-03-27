package org.example.services;

import org.example.entities.User;
import org.example.repositories.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService{
    @Autowired
    usersRepository usersRepository;
    @Override
    public boolean save(User user) {
        try {
            if (checkUserName(user)) {
                usersRepository.save(user);
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExist(String id) {
        return usersRepository.existsById(id);
    }

    @Override
    public User getUser(String name) {
        for (User u : usersRepository.findAll())
            if (u.getNickname().equals(name))
                return u;
        return null;
    }

    @Override
    public boolean changeUser(User user) {
        try {
            usersRepository.save(user);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean isExist(User user) {
        for (User u : usersRepository.findAll())
            if (u.equals(user))
                return true;
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        usersRepository.delete(user);
        return true;
    }

    public List<User> findAll() {return usersRepository.findAll();}

    public boolean checkUserName(User user) {
        for (User u : usersRepository.findAll()){
            if (u.getNickname()!=null&&u.getNickname().equals(user.getNickname()))
                return false;
        }
        return true;
    }
}
