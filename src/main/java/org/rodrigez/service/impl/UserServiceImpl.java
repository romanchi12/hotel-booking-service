package org.rodrigez.service.impl;

import org.rodrigez.model.User;
import org.rodrigez.model.dto.UserDTO;
import org.rodrigez.repository.UserRepository;
import org.rodrigez.service.UserService;
import org.rodrigez.validation.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User add(UserRequest request) {
        User user = create(request);
        return userRepository.save(user);
    }

    @Override
    public User getUser(long userId) {
        return userRepository.getOne(userId);
    }

    private User create(UserRequest userRequest){
        UserDTO dto = new UserDTO(userRequest);

        User user = new User();
        user.setName(dto.getName());

        return user;
    }
}