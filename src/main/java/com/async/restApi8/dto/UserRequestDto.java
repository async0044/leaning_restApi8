package com.async.restApi8.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank String username,
        @NotBlank String password,
        @Email String email
) {

    //здесь можно писать простые мапперы. подумать - переписать ItemResponseDto используя record и насчет маппера внутри dto
}
