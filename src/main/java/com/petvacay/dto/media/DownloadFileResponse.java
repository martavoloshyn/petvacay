package com.petvacay.dto.media;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DownloadFileResponse {

    private String fileDownloadUri;

    private String fileName;

    private long galleryId;

    private String mediaType;
}
