package com.lathief.profile.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tag")
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<Image> images;

    public Tag(String name){
        this.name = name;
    }
}
