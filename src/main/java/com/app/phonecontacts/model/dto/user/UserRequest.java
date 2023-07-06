package com.app.phonecontacts.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "The 'login' cannot be empty")
    private String login;
    private String password;
}
