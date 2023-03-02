package com.lathief.profile.service;

import com.lathief.profile.model.Comment;
import com.lathief.profile.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> getAllComment();
    void createComment(Comment comment);
    List<Comment> getCommentByImage(Image image);
}
