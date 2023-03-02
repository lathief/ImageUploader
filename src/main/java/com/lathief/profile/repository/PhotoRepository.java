package com.lathief.profile.repository;

import com.lathief.profile.model.ProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PhotoRepository extends JpaRepository<ProfilePhoto, Long> {
    @Query(value = "UPDATE ProfilePhoto SET profileImageData=?2 WHERE Id=?1")
    @Modifying
    @Transactional
    void updatePhoto(Long Id, byte[] profileImageData);
}
