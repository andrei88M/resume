package com.example.resume.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "profileName", "status"})
@Builder
@Table(name = "profiles")
public class Profile {

    @Id
    @SequenceGenerator(name = "profile_seq", sequenceName = "profile_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Write your name")
    private String profileName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cell> cells = new ArrayList<>();

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private Image image;
}
