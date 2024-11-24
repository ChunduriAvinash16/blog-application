package com.springboot.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Blog Category DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private long id;

    @Schema(description = "Blog Category Name")
    private String name;

    @Schema(description = "Blog Category Description")
    private String description;
}
