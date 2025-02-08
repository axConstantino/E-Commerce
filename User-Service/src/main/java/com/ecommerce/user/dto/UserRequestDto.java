package com.ecommerce.user.dto;


import com.ecommerce.user.model.Role;
import com.ecommerce.user.validation.NoHtml;
import com.ecommerce.user.validation.View;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequestDto implements Serializable {
    @NotBlank
    @Size(min = 3, max = 64)
    @NoHtml
    private String name;

    @NotBlank
    @NoHtml
    @Size(min = 3, max = 32)
    private String userName;

    @NotBlank
    @Size(max = 128)
    @Email
    @NoHtml
    private String email;

    @NotBlank
    @Size(min = 10, max = 15)
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$")
    @NoHtml
    private String phone;

    @NotBlank(groups = {View.OnCreate.class})
    @Size(min = 8, max = 32, groups = {View.OnCreate.class})
    @JsonView(View.OnCreate.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;
}
