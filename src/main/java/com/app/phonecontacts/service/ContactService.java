package com.app.phonecontacts.service;

import com.app.phonecontacts.exception.DuplicateItemsException;
import com.app.phonecontacts.exception.NullEntityReferenceException;
import com.app.phonecontacts.model.dto.contact.ContactRequest;
import com.app.phonecontacts.model.dto.user.UserRequest;
import com.app.phonecontacts.model.entity.Contact;
import com.app.phonecontacts.model.entity.User;
import com.app.phonecontacts.model.security.UserDetailsImpl;
import com.app.phonecontacts.repository.ContactRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository repository;
    private final UserService userService;

    public Contact create(Contact contact, long ownerId) {
        if (contact != null) {
            isContactWithNameExist(contact.getName());

            contact.setOwner(userService.readById(ownerId));
            return repository.save(contact);
        }
        throw new NullEntityReferenceException("Contact cannot be 'null'");
    }

    public Contact readById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Contact with id " + id + " not found"));
    }

    public Optional<Contact> readByName(String name) {
        return repository.findByName(name);
    }

    public Contact update(Contact contact, long contactId) {
        if (contact != null) {
            isContactWithNameExist(contact.getName());

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

    public ContactRequest hasDuplicate(ContactRequest request) {
        var distinctEmails = request.getEmails().stream().distinct().toList();
        var distinctNumbers = request.getNumbers().stream().distinct().toList();

        if (distinctEmails.size() != request.getEmails().size() ||
            distinctNumbers.size() != request.getNumbers().size()
        ) {
            throw new DuplicateItemsException("Numbers and emails should not be duplicated.");
        }

        return request;
    }

    private void isContactWithNameExist(String name) {
        if (readByName(name).isPresent()) {
            throw new EntityExistsException("Contact with name " + name + " already exists");
        }
    }
}
