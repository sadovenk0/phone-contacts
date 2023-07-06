package com.app.phonecontacts.model.dto.contact;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class ContactResponse {
    private long id;
    @NotBlank(message = "The 'name' cannot be empty")
    private String name;
    private List<String> emails;
    private List<String> numbers;

    public ContactResponse(long id, String name, String emails, String numbers) {
        this.id = id;
        this.name = name;
        this.emails = Arrays.stream(emails.split(",")).map(String::trim).toList();
        this.numbers = Arrays.stream(numbers.split(",")).map(String::trim).toList();
    }
}
