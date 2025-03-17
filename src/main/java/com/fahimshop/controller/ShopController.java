package com.fahimshop.controller;

import com.fahimshop.model.UserD;
import com.fahimshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class ShopController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public String front() {

        return "home";
    }
    @GetMapping(path = "/register")
    public String register() {

        return "register";
    }

    @GetMapping(path =  "/login")
    public String login() {

        return "login";
    }

    @GetMapping(path =  "/products")
    public String products() {

        return "product";
    }

    @GetMapping(path =  "/product")
    public String product() {

        return "view_product";
    }

    @PostMapping("/saveUser")
    public String save(@ModelAttribute UserD user, @RequestParam("images") MultipartFile file, HttpSession session)
            throws IOException {

        String imageName = file.isEmpty() ? "" : file.getOriginalFilename();
        user.setProfileImage(imageName);
        UserD save = userService.save(user);

        if (!ObjectUtils.isEmpty(save)) {
            if (!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/images").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "#" + File.separator
                        + file.getOriginalFilename());

                System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            session.setAttribute("succMsg", "Register successfully");
        } else {
            session.setAttribute("errorMsg", "something wrong on server");
        }

        return "redirect:/register";
    }
}