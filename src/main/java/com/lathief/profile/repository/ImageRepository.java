package com.lathief.profile.repository;

import com.lathief.profile.model.Image;
import com.lathief.profile.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM image WHERE title = ?1")
    Image getByTitle(String title);
    @Query(value = "DELETE FROM Image WHERE title = ?1")
    @Modifying
    @Transactional
    void deleteImage(String title);
    @Query(value = "UPDATE Image SET ImageFile=?4, description=?3, numView=?5, title=?2, uploadDate=?6 WHERE Id = ?1")
    @Modifying
    @Transactional
    void updateImage(Long Id, String title, String description, byte[] ImageFile, int numView, String uploadDate);


}
