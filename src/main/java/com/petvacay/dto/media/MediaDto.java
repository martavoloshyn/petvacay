package com.petvacay.dto.media;

import com.petvacay.entities.Extension;
import com.petvacay.entities.MediaType;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"extension", "mediaType"})
public class MediaDto {
    private long id;

    private String name;

    private Extension extension;

    private String fileId;

    private MediaType mediaType;

    private Timestamp creationDate;

    private long galleryId;
}
