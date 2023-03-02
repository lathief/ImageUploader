package com.lathief.profile.service;

import com.lathief.profile.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService {
    List<Image> getAll();
    List<Image> getByTag(String tagName);
    Image getById(Long Id);
    Image getByTitle(String title);
    Image getByTitleWithJoin(String title);
    void deleteByTitle(String title);
    void save(Image image);
    void update(Image image);
}
