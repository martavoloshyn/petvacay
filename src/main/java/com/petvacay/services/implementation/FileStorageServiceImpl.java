package com.petvacay.services.implementation;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.petvacay.constants.ErrorMessage;
import com.petvacay.dto.media.DownloadFileResponse;
import com.petvacay.dto.media.MediaDto;
import com.petvacay.exceptions.FailedToSetCredentialsException;
import com.petvacay.exceptions.FileStorageException;
import com.petvacay.mappers.media.MediaMapper;
import com.petvacay.services.FileStorageService;
import com.petvacay.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private String fileUploadPath;
    private String BUCKET_NAME;
    private String DIR;
    private String PROJECT_ID;
    private String URL;
    private String ADD_URL;
    private MediaService mediaService;
    private MediaMapper mediaMapper;

    @Autowired
    public FileStorageServiceImpl(MediaServiceImpl mediaService, MediaMapper mediaMapper,
                                  @Value("${file.config-file-path}") String fileUploadPath,
                                  @Value("${file.bucket-name}") String bucketName,
                                  @Value("${file.upload-dir}") String dir,
                                  @Value("${file.project-id}") String projectId,
                                  @Value("${file.download-url}") String url,
                                  @Value("${file.add-to-url}") String addUrl) throws FailedToSetCredentialsException {
        this.mediaService = mediaService;
        this.mediaMapper = mediaMapper;
        this.fileUploadPath = fileUploadPath;
        this.BUCKET_NAME = bucketName;
        this.DIR = dir;
        this.PROJECT_ID = projectId;
        this.URL = url;
        this.ADD_URL = addUrl;
        this.initFirebase();
    }

    public MediaDto storeFile(MultipartFile file, long Id) {
        String fileName = file.getOriginalFilename();
        MediaDto mediaDto = new MediaDto();
        try {
            assert fileName != null;
            if (fileName.contains("..")) {
                throw new FileStorageException(ErrorMessage.INVALID_CHARACTER + fileName);
            }
            mediaDto.setGalleryId(Id);
            MediaDto savedMediaDto = mediaService.saveMedia(mediaDto, fileName);
            String fileIdWithExt = mediaService.fileIDWithExtension(savedMediaDto);
            StorageClient storageClient = StorageClient.getInstance();
            String blobString = DIR + fileIdWithExt;
            Blob blob = storageClient.bucket().create(blobString, file.getInputStream(), Bucket.BlobWriteOption.userProject(PROJECT_ID));
            return savedMediaDto;
        } catch (IOException ex) {
            throw new FileStorageException(fileName + ErrorMessage.COULD_NOT_STORE_FILE);
        }
    }

    private FirebaseApp initFirebase() throws FailedToSetCredentialsException {
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream(fileUploadPath);
        } catch (FileNotFoundException e) {
            throw new FileStorageException(ErrorMessage.FILE_NOT_FOUND + "firebaseConfig.json");
        }
        FirebaseOptions options;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(BUCKET_NAME)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FailedToSetCredentialsException(ErrorMessage.COULD_NOT_SET_CREDENTIALS);
        }

        return FirebaseApp.initializeApp(options);
    }

    public String formDownloadUrl(String fileId) {
        MediaDto mediaDto = mediaService.getDtoForFile(fileId);
        String FileIdWithExt = mediaService.fileIDWithExtension(mediaDto);
        return URL + FileIdWithExt + ADD_URL;
    }

    public DownloadFileResponse getFile(String fileId) {
        MediaDto mediaDto = mediaService.getDtoForFile(fileId);
        DownloadFileResponse fileResponse = new DownloadFileResponse();
        fileResponse.setFileName(mediaDto.getName());
        fileResponse.setMediaType(mediaDto.getMediaType().getType());
        fileResponse.setGalleryId(mediaDto.getGalleryId());
        return fileResponse;
    }

    public List<String> getListOfFilesId(long galleryId) {
        List<MediaDto> mediaDtoList = mediaService.getDtoList(galleryId);
        return getFilesId(mediaDtoList);
    }

    public String getAvatarName(long galleryId) {
        ArrayList<MediaDto> photoDtoList = (ArrayList<MediaDto>) mediaService.getListOfPhotoDto(galleryId);
        MediaDto dto = photoDtoList.get(0);
        return dto.getFileId();
    }

    public boolean delete(long galleryId, String fileName) throws FailedToSetCredentialsException {
        MediaDto mediaDto = mediaMapper.convertToDto(mediaService.getFileByFileIdAndGalleryId(fileName, galleryId));
        StorageClient storageClient = StorageClient.getInstance(initFirebase());
        String blobString = DIR + fileName + "." + mediaDto.getExtension().getName();
        Bucket bucket = storageClient.bucket(BUCKET_NAME);
        Blob blob = bucket.get(blobString);
        boolean result = blob.delete();
        if (result) {
            mediaService.deleteInDB(mediaDto);
        }
        return result;
    }

    private ArrayList<String> getFilesId(List<MediaDto> mediaDtoList) {
        ArrayList<String> fileNames = new ArrayList<>();
        for (MediaDto dto : mediaDtoList) {
            fileNames.add(dto.getFileId());
        }
        return fileNames;
    }
}
