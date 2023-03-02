package com.lathief.profile.controller;

import com.lathief.profile.model.Image;
import com.lathief.profile.model.ImageShow;
import com.lathief.profile.service.ImageService;
import com.lathief.profile.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    ImageService imageService;
    @RequestMapping("/tags/{tagName}")
    public String showImage(@PathVariable String tagName,
                            Model model) {
        List<Image> images = imageService.getByTag(tagName);
        List<ImageShow> imageShows = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            String base64 = Base64.getEncoder().encodeToString(images.get(i).getImageFile());
            imageShows.add(i, new ImageShow(images.get(i).getId(), images.get(i).getTitle(), images.get(i).getDescription(), base64, images.get(i).getNumView(),
                    images.get(i).getUploadDate(), images.get(i).getUser(), images.get(i).getTags()));
        }
        model.addAttribute("images", imageShows);
        model.addAttribute("tag", tagName);

        return "tag/images";
    }
}
