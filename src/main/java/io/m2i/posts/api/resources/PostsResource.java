package io.m2i.posts.api.resources;

import io.m2i.posts.api.dto.PostDTO;
import io.m2i.posts.model.Post;
import io.m2i.posts.service.PostService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/post")
public class PostsResource {

    PostService postService = new PostService();

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAllPosts() {

        List<PostDTO> postDTOList = new ArrayList<>();

        for (Post post: postService.fetchAllPosts()) {
            postDTOList.add(post.toDTO());
        }

        return Response
                .status(Response.Status.CREATED)
                .entity(postDTOList)
                .build();
    }

    @Path("{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getPostById(@PathParam("id") int id) {

        PostDTO postDTO = postService.getPostById(id).toDTO();

        return Response
                .status(Response.Status.CREATED)
                .entity(postDTO)
                .build();
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response createPost(PostDTO dto) {  // JSON ===> JAVA

        Post post = postService.createPost(
                dto.getTitle(),
                dto.getAuthor(),
                dto.getContent(),
                dto.getImgUrl(),
                dto.getCategory().getName()
        );

        return Response
                .status(Response.Status.CREATED)
                .build();
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
    public Response update(@PathParam("id") int id, PostDTO postDTO) {

        Post existingPost = postService.getPostById(id);

        if (existingPost == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        postDTO.setId(existingPost.getId());
        boolean success = postService.updatePost(postDTO);

        System.out.println(success);

        return Response
                .status(Response.Status.OK)
                .build();

    }
}

