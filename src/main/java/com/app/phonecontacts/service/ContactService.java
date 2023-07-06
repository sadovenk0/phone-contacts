package com.app.phonecontacts.service;

import com.app.phonecontacts.exception.NullEntityReferenceException;
import com.app.phonecontacts.model.entity.Contact;
import com.app.phonecontacts.model.entity.User;
import com.app.phonecontacts.model.security.UserDetailsImpl;
import com.app.phonecontacts.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository repository;
    private final UserService userService;

    public Contact create(Contact contact, long ownerId) {
        if (contact != null) {
            contact.setOwner(userService.readById(ownerId));
            return repository.save(contact);
        }
        throw new NullEntityReferenceException("Contact cannot be 'null'");
    }

    public Contact readById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Contact with id " + id + " not found"));
    }

    public Contact update(Contact contact, long contactId) {
        if (contact != null) {
            var existedTask = readById(contactId);
            contact.setId(existedTask.getId());
            contact.setOwner(existedTask.getOwner());
            return repository.save(contact);
        }
        throw new NullEntityReferenceException("Contact cannot be 'null'");
    }

    public void delete(long id) {
        repository.delete(readById(id));
    }

    @PostFilter("filterObject.owner.id == authentication.principal.user.id")
    public List<Contact> getAll() {
        return repository.findAll();
    }
}
