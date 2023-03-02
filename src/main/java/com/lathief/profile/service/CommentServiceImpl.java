package com.lathief.profile.service;

import com.lathief.profile.model.Comment;
import com.lathief.profile.model.Image;
import com.lathief.profile.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }
    public void createComment(Comment comment) {
        commentRepository.save(comment);

    }
    public List<Comment> getCommentByImage(Image image) {
        return commentRepository.getAllComment(image.getId());
    }
}
