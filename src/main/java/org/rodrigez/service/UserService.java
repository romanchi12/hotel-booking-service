package org.rodrigez.service;

import org.rodrigez.model.User;
import org.rodrigez.validation.UserRequest;

public interface UserService {
    // TODO: 16.11.18 add UserResponses
    User add(UserRequest request);
    User getUser(long userId);
}
