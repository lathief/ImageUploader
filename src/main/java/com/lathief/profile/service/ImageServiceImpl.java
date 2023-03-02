package com.lathief.profile.service;

import com.lathief.profile.model.Image;
import com.lathief.profile.model.Tag;
import com.lathief.profile.repository.ImageRepository;
import com.lathief.profile.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    TagRepository tagRepository;

    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    public List<Image> getByTag(String tagName) {
        Tag tag = tagRepository.findTags(tagName);
        List<Long> imageIds = tagRepository.findImages(tag.getId());
        return imageRepository.findAllById(imageIds);
    }

    public Image getById(Long Id) {
        return imageRepository.findById(Id).get();
    }

    public Image getByTitle(String title) {
        return imageRepository.getByTitle(title);
    }

    public Image getByTitleWithJoin(String title) {
        return imageRepository.getByTitle(title);
    }

    public void deleteByTitle(String title) {
       imageRepository.deleteImage(title);
    }

    public void save(Image image) {
        imageRepository.save(image);
    }

    public void update(Image image) {
        if (image.getImageFile() != null){
            image.setUploadDate(new Date().toString());
        }
        imageRepository.updateImage(image.getId(), image.getTitle(), image.getDescription(),image.getImageFile(), image.getNumView(), image.getUploadDate().toString());
    }
}
