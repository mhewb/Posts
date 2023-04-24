package io.m2i.posts.api.resources;

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

    @Path("{id}")
    @DELETE
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deletePostById(@PathParam("id") int id) {  // JSON ===> JAVA

        postService.deletePostById(id);

        return Response
                .status(Response.Status.OK)
                .build();  // JAVA ===> JSON
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Post post) {
        Post existingPost = postService.getPostById(id);

        if (existingPost == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        post.setId(existingPost.getId());
        postService.updatePost(post);
        return Response
                .status(Response.Status.OK)
                .build();

//        return Response.ok(post).build();
    }
}

