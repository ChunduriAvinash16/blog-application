package com.springboot.blog.controller;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
            summary = "Create Comment",
            description = "Create a Comment For Specific Post"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") long id,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(id, commentDto), HttpStatus.CREATED);

    }

    @Operation(
            summary = "Get Comments for Specific Post",
            description = "Get all comments for the post Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 OK"
    )
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(name = "postId") long id) {
        return ResponseEntity.ok(commentService.getAllComments(id));
    }

    @Operation(
            summary = "Get Comment",
            description = "Get specific Comment for the post Id mentioned"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long postId,
                                                     @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @Operation(
            summary = "Update Comment",
            description = "Update Comment for the Post mentioned"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable Long postId,
                                                        @PathVariable Long commentId,
                                                        @Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateCommentById(postId, commentId, commentDto));
    }

    @Operation(
            summary = "Delete Comment",
            description = "Delete Comment for the Post mentioned"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Long postId,
                                                    @PathVariable Long commentId) {
        commentService.deleteCommentById(postId, commentId);
        return ResponseEntity.ok("Comment Deleted");
    }
}
