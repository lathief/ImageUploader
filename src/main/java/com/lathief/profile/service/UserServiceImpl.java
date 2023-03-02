package com.lathief.profile.service;

import com.google.common.hash.Hashing;
import com.lathief.profile.model.ProfilePhoto;
import com.lathief.profile.model.User;
import com.lathief.profile.repository.PhotoRepository;
import com.lathief.profile.repository.UserRepository;
import com.lathief.profile.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    Utils utils;
    public User login(String username, String password) {
        User user = userRepository.getUserByName(username);
        if (user == null){
            return null;
        }

        String hashOfPassword = user.getPassword();
        String hashOfEnteredPassword = Hashing.sha256().hashString(password).toString();

        if (hashOfPassword.equals(hashOfEnteredPassword)) {
            return user;
        } else {
            return null;
        }
    }

    public User getByName(String username) {
        return userRepository.getUserByName(username);
    }

    public User getByNameWithProfilePhoto(String username) {
        User user = userRepository.getUserByName(username);
        Optional<ProfilePhoto> userPhoto = photoRepository.findById(user.getId());
        user.setProfilePhoto(userPhoto.get());
        return user;
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        if (user.getPassword() != null){
            user.setPassword(utils.hashPassword(user.getPassword()));
        }
        userRepository.updateUser(user.getId(), user.getUsername(), user.getPassword(), user.getDescription());
    }
}
