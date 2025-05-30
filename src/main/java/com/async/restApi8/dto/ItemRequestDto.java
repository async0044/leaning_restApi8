package com.async.restApi8.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemRequestDto(
        @NotBlank(message = "Title cannot be empty")
        String title,

        String content,

        @NotBlank(message = "Author cannot be empty")
        String author

) {
}
