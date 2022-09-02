package com.example.resume.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "cellName", "text", "type"})
@Builder
@Table(name = "cells")
public class Cell {

    @Id
    @SequenceGenerator(name = "cell_seq", sequenceName = "cell_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cell_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty
    private String cellName;

    @Column(name = "text")
    @NotEmpty
    private String text;

    @Column(name = "type")
    private boolean type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
