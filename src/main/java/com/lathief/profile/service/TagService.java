package com.lathief.profile.service;

import com.lathief.profile.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    List<Tag> getAll();
    Tag getByName(String title);
    Tag createTag(Tag tag);
    List<Tag> findOrCreateTags(String tagNames);
}
