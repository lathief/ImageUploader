package com.lathief.profile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    private Image image;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    public Comment(String text){
        this.text = text;
    }
    public Comment(String text, User user){
        this.text = text;
        this.user = user;
    }
}
