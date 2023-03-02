package com.lathief.profile.controller;

import com.lathief.profile.model.ProfilePhoto;
import com.lathief.profile.model.User;
import com.lathief.profile.service.ProfilePhotoService;
import com.lathief.profile.service.UserService;
import com.lathief.profile.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ProfilePhotoService profilePhotoService;
    @Autowired
    Utils utils;

    @RequestMapping(value = "/signup")
    public String signUp(HttpSession session) {
        if (session.getAttribute("currUser") == null){
            return "users/signup";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model,
                             HttpSession session) {
        User userFound = userService.getByName(username);
        if (userFound != null) {
            String error = "Username already exists";
            model.addAttribute("error", error);
            return "users/signup";
        }
        ProfilePhoto photo = new ProfilePhoto();
        profilePhotoService.save(photo);

        String passwordHash = utils.hashPassword(password);
        User user = new User(username, passwordHash, photo);
        userService.register(user);

        session.setAttribute("currUser", user);

        return "redirect:/";
    }

    @RequestMapping(value = "/signin")
    public String signIn(HttpSession session) {
        if (session.getAttribute("currUser") == null){
            return "users/signin";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signInUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model,
                             HttpSession session) {
        User user = userService.login(username, password);

        if (user != null ) {
            session.setAttribute("currUser", user);
            return "redirect:/";
        } else {
            String error = "incorrect username or password";
            model.addAttribute("error", error);

            return "users/signin";
        }
    }

    @RequestMapping(value = "/signout")
    public String signOut(HttpSession session) {
        session.removeAttribute("currUser");
        return "redirect:/";
    }

    @RequestMapping(value = "/user/edit_profile")
    public String editProfile(HttpSession session, Model model) {
        User currUser = (User)session.getAttribute("currUser");

        if(currUser == null ){
            return "redirect:/";
        }
        else {
            model.addAttribute("user", currUser);
            return "users/profile.html";
        }
    }

    @RequestMapping(value = "/user/edit_profile", method = RequestMethod.POST)
    public String editUserProfile(@RequestParam("description") String description,
                                  @RequestParam("file") MultipartFile file,
                                  HttpSession session) throws IOException {
        User currUser = (User)session.getAttribute("currUser");

        ProfilePhoto photo = currUser.getProfilePhoto();
        byte[] photoDataAsBase64 = file.getBytes();
        photo.setprofileImageData(photoDataAsBase64);
        profilePhotoService.update(photo);

        currUser.setDescription(description);
        currUser.setProfilePhoto(photo);
        userService.update(currUser);

        return "redirect:/";
    }

}
