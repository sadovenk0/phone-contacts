package com.app.phonecontacts.service;

import com.app.phonecontacts.exception.NullEntityReferenceException;
import com.app.phonecontacts.model.entity.Contact;
import com.app.phonecontacts.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactService {
    private final ContactRepository repository;

    public Contact create(Contact contact) {
        if (contact != null) {
            contact.setOwner("isNeededToBeChanged");
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
            return repository.save(contact);
        }
        throw new NullEntityReferenceException("Contact cannot be 'null'");
    }

    public void delete(long id) {
        Contact contact = readById(id);
        repository.delete(contact);
    }

    // ToDo add PostFilter for retrieving relevant list of contacts (for certain user)
    public List<Contact> getAll() {
        return repository.findAll();
    }
}
