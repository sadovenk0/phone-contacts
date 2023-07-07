package com.app.phonecontacts.model.dto.contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ContactRequest {
    @NotBlank(message = "The 'name' cannot be empty")
    private String name;
    private List<@Email(message = "Invalid email format") String> emails;
    private List<@Pattern(regexp = "(^$|^(\\+38)?0[0-9]{9}$)", message = "Invalid number format")String> numbers;
}
