package com.fahimshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    @GetMapping("/")
    public String admin_dashbord(){
        return "admin/admin_dashbord";
    }

    @GetMapping("/add_product")
    public String add_product(){
        return "admin/add_product";
    }

    @GetMapping("/product_category")
    public String category (){
        return "admin/category";
    }

}
