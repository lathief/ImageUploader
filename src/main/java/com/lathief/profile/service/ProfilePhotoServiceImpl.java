package com.lathief.profile.service;

import com.lathief.profile.model.ProfilePhoto;
import com.lathief.profile.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ProfilePhotoServiceImpl implements ProfilePhotoService{
    @Autowired
    PhotoRepository photoRepository;
    public void save(ProfilePhoto image) {
        photoRepository.save(image);
    }

    public void update(ProfilePhoto image) {

        if (image.getProfileImageData() != null){
            photoRepository.updatePhoto(image.getId(), image.getProfileImageData());
        }
    }
}
