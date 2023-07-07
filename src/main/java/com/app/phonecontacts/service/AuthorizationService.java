package com.app.phonecontacts.service;

import com.app.phonecontacts.model.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final ContactService contactService;
    public boolean isOwner(long contactId) {
        var contact = contactService.readById(contactId);
        var principle = (UserDetailsImpl) getPrinciple();
        return Objects.equals(contact.getOwner().getId(), principle.getUser().getId());
    }

    public Object getPrinciple() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
