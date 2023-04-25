package io.m2i.posts.api.resources;
import io.m2i.posts.api.dto.CategoryDTO;
import io.m2i.posts.model.Category;
import io.m2i.posts.service.CategoryService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/category")
public class CategoryResource {

    CategoryService categoryService = new CategoryService();

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAllCategories() {

        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (Category category: categoryService.fetchAllCategories()) {
            categoryDTOList.add(category.toDTO());
        }

        return Response
                .status(Response.Status.CREATED)
                .entity(categoryDTOList)
                .build();
    }

    @Path("{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") int id) {

        CategoryDTO categoryDTO = categoryService.getCategoryById(id).toDTO();

        return Response
                .status(Response.Status.CREATED)
                .entity(categoryDTO)
                .build();
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response createCategory(CategoryDTO dto) {  // JSON ===> JAVA

        Category category = categoryService.createCategory(
                dto.getName()
        );

        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    @Path("{id}")
    @DELETE
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteCategoryById(@PathParam("id") int id) {  // JSON ===> JAVA

        categoryService.deleteCategoryById(id);

        return Response
                .status(Response.Status.OK)
                .build();  // JAVA ===> JSON
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, CategoryDTO categoryDTO) {

        Response.Status status = null;
        Category existingCategory = categoryService.getCategoryById(id);

        if (existingCategory == null) {
            status = Response.Status.NOT_FOUND;
        } else {

            categoryDTO.setId(existingCategory.getId());
            boolean success = categoryService.updateCategoryById(categoryDTO.getId(), categoryDTO.getName());

            if (success) {
                status = Response.Status.OK;

            } else {
                status = Response.Status.NOT_MODIFIED;
            }
        }

        return Response
                .status(status)
                .build();


    }

}

