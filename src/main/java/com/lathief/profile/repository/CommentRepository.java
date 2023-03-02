package com.lathief.profile.repository;

import com.lathief.profile.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM comment WHERE image_id = ?1")
    List<Comment> getAllComment(Long image_id);
}
