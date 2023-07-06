package com.app.phonecontacts.model.dto.contact;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class ContactRequest {
    @NotBlank(message = "The 'name' cannot be empty")
    private String name;
    private List<String> emails;
    private List<String> numbers;
}
