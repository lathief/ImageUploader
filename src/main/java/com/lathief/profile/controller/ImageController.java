package com.lathief.profile.controller;

import com.lathief.profile.model.Image;
import com.lathief.profile.model.ImageShow;
import com.lathief.profile.model.Tag;
import com.lathief.profile.model.User;
import com.lathief.profile.service.CommentService;
import com.lathief.profile.service.ImageService;
import com.lathief.profile.service.TagService;
import com.lathief.profile.service.UserService;
import com.lathief.profile.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Controller
public class ImageController {
    @Autowired
    ImageService imageService;
    @Autowired
    TagService tagService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    Utils utils;

    @RequestMapping("/")
    public String listImages(Model model) {
        List<Image> images = imageService.getAll();
        List<ImageShow> imageShows = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            String base64 = Base64.getEncoder().encodeToString(images.get(i).getImageFile());
            imageShows.add(i, new ImageShow(images.get(i).getId(), images.get(i).getTitle(), images.get(i).getDescription(), base64, images.get(i).getNumView(),
                    images.get(i).getUploadDate(), images.get(i).getUser(), images.get(i).getTags()));
        }
        model.addAttribute("images", imageShows);
        return "home";
    }

    @RequestMapping("/images/upload")
    public String uploadImage(HttpSession session) {
        User currUser = (User) session.getAttribute("currUser");

        if(currUser == null ){
            return "redirect:/";
        }
        else {
            return "images/upload";
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("title") String title,
                         @RequestParam("description") String description,
                         @RequestParam("file") MultipartFile file,
                         @RequestParam("tags") String tags,
                         HttpSession session) throws IOException {
        User currUser = (User) session.getAttribute("currUser");
        if(currUser == null ){
            return "redirect:/";
        }
        else {
            List<Tag> imageTags = tagService.findOrCreateTags(tags);
            byte[] uploadedImageData = file.getBytes();

            Image newImage = new Image(title, description, uploadedImageData, currUser, imageTags);
            imageService.save(newImage);

            return "redirect:/images/" + newImage.getTitle();
        }
    }

    @RequestMapping("/images/{title}")
    public String showImage(@PathVariable String title, Model model) {
        Image image = imageService.getByTitleWithJoin(title);
        image.setNumView(image.getNumView() + 1);
        String base64 = Base64.getEncoder().encodeToString(image.getImageFile());
        ImageShow imageShow = new ImageShow(image.getId(), image.getTitle(), image.getDescription(), base64, image.getNumView(),
                image.getUploadDate(), image.getUser(), image.getTags());
        imageService.update(image);

        model.addAttribute("user", image.getUser());
        model.addAttribute("userProfilePhoto", Base64.getEncoder().encodeToString(image.getUser().getProfilePhoto().getProfileImageData()));
        model.addAttribute("image", imageShow);
        model.addAttribute("tags", image.getTags());
        model.addAttribute("comments", commentService.getCommentByImage(image));

        return "images/image";
    }

    @RequestMapping("/images/{title}/delete")
    public String deleteImage(@PathVariable String title) {
        Image image = imageService.getByTitle(title);
        imageService.deleteByTitle(title);


        return "redirect:/";
    }

    @RequestMapping("/images/{title}/edit")
    public String editImage(@PathVariable String title, Model model) {
        Image image = imageService.getByTitleWithJoin(title);
        String tags = utils.convertTagsToString(image.getTags());
        String base64 = Base64.getEncoder().encodeToString(image.getImageFile());
        ImageShow imageShow = new ImageShow(image.getId(), image.getTitle(), image.getDescription(), base64, image.getNumView(),
                image.getUploadDate(), image.getUser(), image.getTags());
        model.addAttribute("image", imageShow);
        model.addAttribute("tags", tags);

        return "images/edit";
    }

    @RequestMapping(value = "/editImage", method = RequestMethod.POST)
    public String edit(@RequestParam("title") String title,
                       @RequestParam("description") String description,
                       @RequestParam("file") MultipartFile file,
                       @RequestParam("tags") String tags) throws IOException {
        Image image = imageService.getByTitle(title);
        List<Tag> imageTags = tagService.findOrCreateTags(tags);
        byte[] updatedImageData = file.getBytes();

        image.setDescription(description);
        image.setImageFile(updatedImageData);
        image.setTags(imageTags);
        imageService.update(image);

        return "redirect:/images/" + title;
    }
}
