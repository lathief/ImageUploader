package com.lathief.profile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageShow {
    private Long id;
    private String title;
    private String description;
    private String ImageFile;
    private int numView;
    private String uploadDate;
    private User user;
    private List<Tag> tags = new ArrayList<Tag>();
    public ImageShow(String title, String description, String imageFile, User user, List<Tag> tags) {
        this.description = description;
        this.title = title;
        this.ImageFile = imageFile;
        this.numView = 0;
        this.user = user;
        this.uploadDate = new Date().toString();
        this.tags = tags;
    }
}
