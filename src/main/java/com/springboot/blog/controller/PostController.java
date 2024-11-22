package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
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
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    /***
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    /***
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    /***
     *
     * @param id
     * @param postDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable long id,
                                                  @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePostById(id, postDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Post is Deleted Successfully");
    }
}
