package com.petvacay.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "media")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(exclude = {"name", "mediaType", "project", "storyBoard"})
//@ToString(exclude = {"project", "storyBoard", "mediaType"})
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "file_id", length = 36, nullable = false)
    private String fileId;

    @Column(name = "create_date")
    private Timestamp creationDate;

    @ManyToOne
    @JoinColumn(name = "media_type_id")
    private MediaType mediaType;

    @ManyToOne
    @JoinColumn(name = "extension_id")
    private Extension extension;

    @ManyToOne
    @JoinColumn(name = "gallery_id", nullable = false)
    private Gallery gallery;
}
