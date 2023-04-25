package io.m2i.posts.service;

import io.m2i.posts.dao.CategoryDAO;
import io.m2i.posts.dao.CategoryJdbcDAO;
import io.m2i.posts.model.Category;

import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO = new CategoryJdbcDAO();

    public List<Category> fetchAllCategories() {return categoryDAO.findAll(); }

    public Category getCategoryById(Integer id) { return categoryDAO.getById(id); }

    public void createCategory(String name) {
        Category category = new Category(name);
        categoryDAO.create(category);
    }

    public void updateCategoryById(int id, String name) {
        Category category = new Category(id, name);
        categoryDAO.update(category)
        ;}

    public void deleteCategoryById(int id) {
        Category category = getCategoryById(id);
        categoryDAO.delete(category)
        ;}

}
