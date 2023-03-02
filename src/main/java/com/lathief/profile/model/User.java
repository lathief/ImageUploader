package com.lathief.profile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String username;
    private String password;
    private String description;
    @OneToOne(fetch = FetchType.EAGER)
    private ProfilePhoto profilePhoto;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Image> images = new ArrayList<Image>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
    public User(String username, String passwordHash, ProfilePhoto photo) {
        this.username = username;
        this.password = passwordHash;
        this.profilePhoto = photo;
    }
}
