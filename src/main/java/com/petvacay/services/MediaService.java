package com.petvacay.services;

import com.petvacay.dto.media.MediaDto;
import com.petvacay.entities.Media;

import java.util.List;

public interface MediaService {

    MediaDto saveMedia(MediaDto mediaDto, String fileName);

    String fileIDWithExtension(MediaDto savedMediaDto);

    MediaDto getDtoForFile(String fileId);

    List<MediaDto> getDtoList(long galleryId);

    Object getListOfPhotoDto(long galleryId);

    Media getFileByFileIdAndGalleryId(String fileName, long galleryId);

    void deleteInDB(MediaDto mediaDto);
}
