package com.lathief.profile.service;

import com.lathief.profile.model.ProfilePhoto;
import org.springframework.stereotype.Service;

@Service
public interface ProfilePhotoService {
    void save(ProfilePhoto image);
    void update(ProfilePhoto image);
}
