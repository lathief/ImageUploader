package com.lathief.profile.repository;

import com.lathief.profile.model.Image;
import com.lathief.profile.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tag WHERE name = ?1")
    Tag findTags(String title);
    @Query(nativeQuery = true, value = "SELECT image_id FROM image_tag WHERE tag_id = ?1")
    List<Long> findImages(Long Id);
    @Query(nativeQuery = true, value = "SELECT * FROM tag WHERE name = ?1")
    List<Image> findImages(String tag);
}
