package com.example.resume.model;

import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "image")
public class Image {

    @Id
    @SequenceGenerator(name = "image_seq", sequenceName = "image_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String imageName;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "size")
    private Long size;

    @Column(name ="content_type")
    private String contentType;

    @Column(name = "bytes", columnDefinition = "LONGBLOB")
    private byte[] bytes;

    @OneToOne
    private Profile profile;

}
