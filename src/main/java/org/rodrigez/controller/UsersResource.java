package org.rodrigez.controller;

import org.rodrigez.model.User;
import org.rodrigez.model.dto.UserDTO;
import org.rodrigez.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersResource {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable(value = "userId") long userId){
        User user = userService.getUser(userId);
        System.out.println(user);
        return new UserDTO(user);
    }
}