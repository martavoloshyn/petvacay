package com.petvacay.services;

import com.petvacay.dto.media.DownloadFileResponse;
import com.petvacay.dto.media.MediaDto;
import com.petvacay.exceptions.FailedToSetCredentialsException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {

    MediaDto storeFile(MultipartFile file, long projectId);

    DownloadFileResponse getFile(String fileId);

    List<String> getListOfFilesId(long galleryId);

    String getAvatarName(long galleryId);

    boolean delete(long galleryId, String fileName) throws FailedToSetCredentialsException;
}
