package io.m2i.posts.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.m2i.posts.model.Post;
import io.m2i.posts.service.PostService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/posts")
public class PostsResource {

    PostService postService = new PostService();

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAllPosts() {
        return Response
                .status(Response.Status.CREATED)
                .entity(postService.fetchAllPosts())
                .build();  // JAVA ===> JSON
    }

    @Path("{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getPostById(@PathParam("id") int id) {
        return Response
                .status(Response.Status.CREATED)
                .entity(postService.getPostById(id))
                .build();  // JAVA ===> JSON
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response createPost(Post post) {  // JSON ===> JAVA

        postService.createPost(post);

        return Response
                .status(Response.Status.CREATED)
                .entity(post)
                .build();  // JAVA ===> JSON
    }
}

