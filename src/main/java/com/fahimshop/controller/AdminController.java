package com.fahimshop.controller;

import com.fahimshop.model.Category;
import com.fahimshop.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
    public String category(Model model) {
        model.addAttribute("category",categoryService.getAllCategory());
        return "admin/category";
    }

    @PostMapping("/saveCategory")

    public String saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {

        String imageName = file != null ? file.getOriginalFilename() : "default.jpg";
        category.setImageName(imageName);

        Boolean existCategory = categoryService.existCategory(category.getName());
        if (existCategory) {
            session.setAttribute("errorMsg", "Category already exist");
        } else {

            Category saveCategory = categoryService.saveCategory(category);
            if (ObjectUtils.isEmpty(saveCategory)) {

                session.setAttribute("errorMsg", "Not saved ! internal server error");
            } else {

                File saveFile = new ClassPathResource("static/images").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "category_img" + File.separator
                        + file.getOriginalFilename());

                System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);


                session.setAttribute("succMsg", "Category saved successfully");

            }

        }

        return "redirect:/admin/category";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id, HttpSession session) {
        Boolean deleteCategory = categoryService.deleteCategory(id);

        if (deleteCategory) {
            session.setAttribute("succMsg", "category delete success");
        } else {
            session.setAttribute("errorMsg", "something wrong on server");
        }

        return "redirect:/admin/category";
    }

    @GetMapping("/loadEditCategory/{id}")
    public String loadEditCategory(@PathVariable int id, Model model) {
        model.addAttribute("category",categoryService.getCategoryById(id));
        return "admin/edit_category";
    }
    @PostMapping("/updateCategory")
    public String updateCategory(@ModelAttribute Category category,@RequestParam("file") MultipartFile file, HttpSession session)   {
 Category oldocategory =   categoryService.getCategoryById(category.getId());
 String imageName = file.isEmpty() ? oldocategory.getImageName(): file.getOriginalFilename();
 if (ObjectUtils.isEmpty(category)) {

     oldocategory.setName(category.getName());
     oldocategory.setIsActive(category.getIsActive());
     oldocategory.setImageName(imageName);
 }
    Category updateCategory = categoryService.saveCategory(oldocategory);
    if(!ObjectUtils.isEmpty(updateCategory)) {
        session.setAttribute("succMsg", "category update success");
    }else {
        session.setAttribute("errorMsg", "something wrong on server");
    }
       return "redirect:/admin/loadEditCategory" +category.getId();
}

}
