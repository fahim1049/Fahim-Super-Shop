package com.fahimshop.service;

import com.fahimshop.model.Category;

import java.util.List;

public interface CategoryService {

    public Category save(Category category);

    public Boolean existCategory(String Name);

    public List<Category> getAllCategory();

}
