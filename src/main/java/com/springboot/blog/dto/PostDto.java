package com.springboot.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Schema(description = "Blog Post DTO")
@Data
public class PostDto {
    private long id;

    @Schema(description = "Blog Post Title")
    @NotEmpty
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;

    @Schema(description = "Blog Post Description")
    @NotEmpty
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;

    @Schema(description = "Blog Post Content")
    @NotEmpty
    private String content;

    private Set<CommentDto> comments;

    @Schema(description = "Blog Post Category")
    private Long categoryId;
}
