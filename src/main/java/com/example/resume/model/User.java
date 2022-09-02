package com.example.resume.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(of = {"id", "username", "roles"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty(message = "You need to write a login")
    @Size(min = 3, message = "More characters are needed")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "You need to write a password")
    @Size(min = 3, message = "More characters are needed")
    private String password;

    @Transient
    @NotEmpty(message = "You need to write a password2")
    @Size(min = 3, message = "More characters are needed")
    private String password2;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Profile> profiles;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;


}
