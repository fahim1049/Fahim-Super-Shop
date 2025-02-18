package com.fahimshop.controller;

import com.fahimshop.model.Category;
import com.fahimshop.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String admin_dashbord() {
        return "admin/admin_dashbord";
    }

    @GetMapping("/add_product")
    public String add_product() {
        return "admin/add_product";
    }

    @GetMapping("/category")
    public String category() {
        return "admin/category";
    }
    @PostMapping("/saveCategory")

    public String saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file, HttpSession session) {

       String imageName =  file!= null ? file.getOriginalFilename(): "default.jpg";
        category.setImageName(imageName);

        Boolean existCategory = categoryService.existCategory(category.getName());
        if(existCategory) {
            session.setAttribute("errorMsg","Category already exist");
        }
        else {

            Category saveCategory = categoryService.save(category);
            if(ObjectUtils.isEmpty(saveCategory)){

                session.setAttribute("errorMsg","Not saved ! internal server error");
            }else{

                session.setAttribute("succMsg","Category saved successfully");

            }

        }

        return "redirect:/admin/category";
    }

}
