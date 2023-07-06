package com.app.phonecontacts.controller;

import com.app.phonecontacts.model.dto.contact.ContactMapper;
import com.app.phonecontacts.model.dto.contact.ContactRequest;
import com.app.phonecontacts.model.dto.contact.ContactResponse;
import com.app.phonecontacts.service.ContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactMapper mapper;
    private final ContactService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponse create(
            @RequestBody @Valid ContactRequest request
    ) {
//        var contact = mapper.contactRequestToContact(request);
//        return mapper.contactToContactResponse(service.create(contact));
        return new ContactResponse();
    }

    @PutMapping("/{id}")
    public ContactResponse update(
            @RequestBody @Valid ContactRequest request,
            @PathVariable long id
    ) {
        // ToDo
        return new ContactResponse();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable long id
    ) {
        // ToDo
    }

    @GetMapping("/all")
    public List<ContactResponse> getAll() {
        // ToDo
        return List.of(new ContactResponse());
    }
}
