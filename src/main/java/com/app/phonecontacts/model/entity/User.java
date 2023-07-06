package com.app.phonecontacts.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Login must not be empty")
    @Size(min = 2, max = 20, message = "The login must be between 2 and 20 characters long")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Pattern(regexp = ".*[a-z].*",
            message = "Must contain at least one lowercase letter")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private String role;

    //@OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    //private List<Contact> contacts;

}
