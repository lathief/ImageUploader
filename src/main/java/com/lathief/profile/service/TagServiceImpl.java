package com.lathief.profile.service;

import com.lathief.profile.model.Tag;
import com.lathief.profile.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagRepository tagRepository;
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
    public Tag getByName(String title) {
        return tagRepository.findTags(title);
    }
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }
    public List<Tag> findOrCreateTags(String tagNames) {
        // converts a comma delimited String into a String Tokenizer
        StringTokenizer st = new StringTokenizer(tagNames, ",");
        List<Tag> tags = new ArrayList<>();

        // for each token in the Tokenizer
        while(st.hasMoreTokens()) {
            // trim any white spaces before or after the String token
            String tagName = st.nextToken().trim();
            // check if the associated Tag has been created and stored in the database
            Tag tag = tagRepository.findTags(tagName);

            // if the associated Tag has not been created, create the tag
            // and store it in the database
            if(tag == null) {
                Tag newTag = new Tag(tagName);
                tag = tagRepository.save(newTag);
            }

            tags.add(tag);
        }

        return tags;
    }

}
