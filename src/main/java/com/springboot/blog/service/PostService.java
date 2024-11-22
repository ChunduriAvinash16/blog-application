package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.model.Post;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();
    PostDto getPostById(long id);

    PostDto updatePostById(long id, PostDto postDto);

    void deletePostById(long id);
}
