package com.lathief.profile.service;

import com.lathief.profile.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User login(String username, String password);
    User getByName(String username);
    User getByNameWithProfilePhoto(String username);
    void register(User user);
    void update(User user);
}
