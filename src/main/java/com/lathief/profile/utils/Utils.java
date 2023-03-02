package com.lathief.profile.utils;

import com.google.common.hash.Hashing;
import com.lathief.profile.model.Tag;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component
public class Utils {
    public String convertUploadedFileToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }
    public String hashPassword(String password) {

        return Hashing.sha256()
                .hashString(password)
                .toString();
    }
    public String convertTagsToString(List<Tag> tags) {
        StringBuilder tagString = new StringBuilder();

        for(int i = 0; i <= tags.size() - 2; i++) {
            tagString.append(tags.get(i).getName()).append(", ");
        }

        Tag lastTag = tags.get(tags.size() - 1);
        tagString.append(lastTag.getName());

        return tagString.toString();
    }
}
