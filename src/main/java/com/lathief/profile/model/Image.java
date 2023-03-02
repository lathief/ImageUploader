package com.lathief.profile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Lob
    private byte[] ImageFile;
    private int numView;
    private String uploadDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Image_Tag",
            joinColumns = {@JoinColumn(name = "image_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<Tag> tags = new ArrayList<Tag>();

    public Image(String title, String description, byte[] imageFile, User user, List<Tag> tags) {
        this.description = description;
        this.title = title;
        this.ImageFile = imageFile;
        this.numView = 0;
        this.user = user;
        this.uploadDate = new Date().toString();
        this.tags = tags;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "image", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
