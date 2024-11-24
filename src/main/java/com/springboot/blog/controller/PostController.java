package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.ApplicationConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@Tag(name = "CRUD APIs for Post Resource")
public class PostController {
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /***
     *
     * @param postDto
     * @return
     */
    @Operation(
            summary = "Create Post Rest APIs",
            description = "Create Post Rest API is used to save the post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    /***
     *
     * @return
     */
    @Operation(
            summary = "Get All Posts",
            description = "Get All Post with Pagination"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http 200 OK "
    )
    @GetMapping("/api/v1/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ApplicationConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ApplicationConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir));
    }

    /***
     *
     * @param id
     * @return
     */
    @Operation(
            summary = "Get Post Get API",
            description = "Get Post Rest API is used to get the post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    //@GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=1")
    @GetMapping(value = "/api/posts/{id}", produces = "application/vnd.blog.v1+json")
    //Accept : application/vnd.blog.v1+json
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    /***
     *
     * @param id
     * @param postDto
     * @return
     */
    @Operation(
            summary = "Update Post",
            description = "Update Post By PostId"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 OK- updated"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable long id,
                                                  @Valid @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePostById(id, postDto));
    }

    @Operation(
            summary = "Delete Post",
            description = "Delete Post By Post Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http 200 OK - deleted"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("haRole('ADMIN'")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Post is Deleted Successfully");
    }

    @Operation(
            summary = "Get Post By CategoryID",
            description = "Get Post By CategoryID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/api/v1/posts/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategoryIf(@PathVariable long categoryId) {
        return ResponseEntity.ok(postService.getPostsByCategory(categoryId));
    }
}
