package org.example.lab9.domain;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Privilage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
