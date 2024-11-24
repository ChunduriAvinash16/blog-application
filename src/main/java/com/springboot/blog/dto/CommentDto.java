package com.springboot.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Blog Comment DTI")
@Data
public class CommentDto {
    private long id;

    @Schema(description = "Blog Comment name")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @Schema(description = "Blog Comment email")
    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Email is Invalid")
    private String email;

    @Schema(description = "Blog Comment body")
    @NotEmpty
    @Size(min = 10, message = "Comment body should be minimum 10 characters")
    private String body;
}
