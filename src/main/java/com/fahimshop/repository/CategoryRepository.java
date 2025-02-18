package com.fahimshop.repository;


import com.fahimshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Boolean existsByName(String name);
}
