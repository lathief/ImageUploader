package com.lathief.profile.controller;

import com.lathief.profile.model.Comment;
import com.lathief.profile.model.Image;
import com.lathief.profile.model.User;
import com.lathief.profile.service.CommentService;
import com.lathief.profile.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    ImageService imageService;
    @Autowired
    CommentService commentService;
    @RequestMapping(value = "/image/{title}/comments/create", method = RequestMethod.POST)
    public String createComment(@PathVariable String title, @RequestParam("comment") String commentText, HttpSession session) {
        User currUser = (User) session.getAttribute("currUser");
        if (currUser == null) {
            return "redirect:/";
        }

        Image image = imageService.getByTitle(title);
        Comment comment = new Comment();
        comment.setText(commentText);
        comment.setImage(image);
        comment.setUser(currUser);
        commentService.createComment(comment);
        return "redirect:/images/" + image.getTitle();
    }
}
