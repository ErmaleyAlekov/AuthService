package org.example.controllers;

import org.example.entities.User;
import org.example.entities.UserCreater;
import org.example.services.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.example.utils.converter;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    userServiceImpl userService;

    @PostMapping(value = "/saveUser")
    public boolean saveUser(@RequestBody @NonNull UserCreater userCreater) {
        User user = new User();
        converter.convert(user, userCreater);
       return userService.save(user);
    }
    @GetMapping(value = "/getUser/{name}")
    public User getUserByName(@PathVariable String name){
        return userService.getUser(name);
    }

    @PostMapping(value = "/changeUser")
    public boolean changeUser(@RequestBody User user) {
        return userService.changeUser(user);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody UserCreater user) {
        for (User u : userService.findAll()){
            if (u.getNickname().equals(user.getNickname())
                    && u.getPassword().equals(user.getPassword())){
                String logId = UUID.randomUUID()+"";
                u.setLoginId(logId);
                userService.changeUser(u);
                return logId;
            }
        }
        return "Wrong user data.";
    }

    @PostMapping(value = "/logout")
    public String logout(@RequestBody String logId) {
        for (User u : userService.findAll()){
            if (u.getLoginId()!=null&&u.getLoginId().equals(logId)){
                u.setLoginId("");
                userService.changeUser(u);
                return "success";
            }
        }
        return "Cannot find this user by login id.";
    }
    @PostMapping(value = "/deleteUser")
    public boolean deleteUser(@RequestBody User user){
        return userService.deleteUser(user);
    }
}
