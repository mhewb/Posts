package io.m2i.posts.model;

import io.m2i.posts.api.dto.CategoryDTO;
import io.m2i.posts.api.dto.PostDTO;

public class Category {

    private int id;
    private String name;

    public Category() {}

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public CategoryDTO toDTO() {

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(this.id);
        categoryDTO.setName(this.name);

        return categoryDTO;

    };

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
