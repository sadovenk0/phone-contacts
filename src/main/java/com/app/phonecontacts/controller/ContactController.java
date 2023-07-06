package com.app.phonecontacts.controller;

import com.app.phonecontacts.model.dto.contact.ContactMapper;
import com.app.phonecontacts.model.dto.contact.ContactRequest;
import com.app.phonecontacts.model.dto.contact.ContactResponse;
import com.app.phonecontacts.model.security.UserDetailsImpl;
import com.app.phonecontacts.service.AuthorizationService;
import com.app.phonecontacts.service.ContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactMapper mapper;
    private final ContactService service;
    private final AuthorizationService auth;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponse create(
            @RequestBody @Valid ContactRequest request
    ) {
        var ownerId = ((UserDetailsImpl) auth.getPrinciple()).getUser().getId();
        var contact = mapper.contactRequestToContact(request);
        return mapper.contactToContactResponse(service.create(contact, ownerId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("@authorizationService.isOwner(#id)")
    public ContactResponse update(
            @RequestBody @Valid ContactRequest request,
            @PathVariable long id
    ) {
        var contactToUpdate = mapper.contactRequestToContact(request);
        return mapper.contactToContactResponse(service.update(contactToUpdate, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("@authorizationService.isOwner(#id)")
    public void delete(
            @PathVariable long id
    ) {
        service.delete(id);
    }

    @GetMapping("/all")
    public List<ContactResponse> getAll() {
        return service.getAll().stream().map(mapper::contactToContactResponse).toList();
    }
}
