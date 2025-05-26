package com.async.restApi8.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemRequestDto(
        @NotBlank String title,
        String content,
        @NotBlank String author
) {
}
