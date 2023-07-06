package com.app.phonecontacts.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @Size(min = 4, max = 30, message = "This is required field min 4 symbols")
    private String name;

    @Column(name = "emails")
    private String emails;

    @Column(name = "numbers")
    private String numbers;

    // TODO: change to User entity + add ManyToOne
    @Column(name = "owner_id", nullable = false)
    private String owner;
}
