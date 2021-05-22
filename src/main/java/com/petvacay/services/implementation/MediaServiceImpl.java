package com.petvacay.services.implementation;


import com.petvacay.constants.ErrorMessage;
import com.petvacay.dto.media.MediaDto;
import com.petvacay.entities.Extension;
import com.petvacay.entities.Media;
import com.petvacay.entities.MediaType;
import com.petvacay.exceptions.FileStorageException;
import com.petvacay.exceptions.NotFoundException;
import com.petvacay.mappers.media.MediaMapper;
import com.petvacay.repositories.*;
import com.petvacay.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MediaServiceImpl implements MediaService {

    private MediaTypeRepository mediaTypeRepository;
    private MediaRepository mediaRepository;
    private ExtensionRepository extensionRepository;
    private MediaMapper mediaMapper;
    private UserRepository userRepository;
    private PetCheckRepository petCheckRepository;
    private GalleryRepository galleryRepository;

    @Autowired
    public MediaServiceImpl(MediaTypeRepository mediaTypeRepository, MediaRepository mediaRepository,
                            ExtensionRepository extensionRepository, MediaMapper mediaMapper,
                            UserRepository userRepository, PetCheckRepository petCheckRepository,
                            GalleryRepository galleryRepository) {
        this.mediaTypeRepository = mediaTypeRepository;
        this.mediaRepository = mediaRepository;
        this.extensionRepository = extensionRepository;
        this.mediaMapper = mediaMapper;
        this.userRepository = userRepository;
        this.petCheckRepository = petCheckRepository;
        this.galleryRepository = galleryRepository;
    }

    @Transactional
    public MediaDto saveMedia(MediaDto mediaDto, String fileName) {
        Media mediaModel = createMediaModelFromDtoData(mediaDto, fileName);
        mediaRepository.save(mediaModel);
        return mediaMapper.convertToDto(mediaRepository.getByFileId(mediaModel.getFileId()));
    }

    private Media createMediaModelFromDtoData(MediaDto mediaDto, String fileName) {
        mediaDto.setName(fileName);
        mediaDto.setFileId(generateFileId());
        String ext = getFileExtension(fileName);
        Extension extension = extensionRepository.findByName(ext);
        mediaDto.setExtension(extension);
        if (mediaDto.getExtension() == null) {
            throw new NotFoundException(ErrorMessage.SUCH_EXTENSION_IS_NOT_ALLOWED);
        }
        MediaType mediaType = extension.getMediaType();
        mediaDto.setMediaType(mediaType);
        Media mediaModel = mediaMapper.convertToModel(mediaDto);
        mediaModel.setCreationDate(new Timestamp(new Date().getTime()));
        return mediaModel;
    }

    public String fileIDWithExtension(MediaDto mediaDto) {
        List<String> name = Arrays.asList(mediaDto.getFileId(), mediaDto.getExtension().getName());
        return String.join(".", name);
    }

    private String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        else return "";
    }

    private String generateFileId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public Media getFileByFileIdAndGalleryId(String fileName, long galleryId) {
        Media media = mediaRepository.getFileByFileIdAndGalleryId(fileName, galleryId);
        if (media == null) {
            throw new FileStorageException(ErrorMessage.FILE_NOT_FOUND_BY_NAME_AND_USER_ID + fileName + ", id " + galleryId);
        }
        return media;
    }

    public List<MediaDto> getListOfPhotoDto(long galleryId) {
        MediaType mediaType = mediaTypeRepository.findByType("photo");
        return mediaMapper.convertListToDto(mediaRepository.getPhotosByGalleryIdAndMediaType(galleryId, mediaType));
    }

    public List<MediaDto> getDtoList(long galleryId) {
        return mediaMapper.convertListToDto(mediaRepository.getFilesByGalleryId(galleryId));
    }

    public MediaDto getDtoForFile(String fileId) {
        return mediaMapper.convertToDto(mediaRepository.getByFileId(fileId));
    }

    public void deleteInDB(MediaDto dto) {
        Media mediaToDelete = mediaMapper.convertToModel(dto);
        mediaRepository.delete(mediaToDelete);
    }

    public long getUserGalleryId(long userId) {
        return userRepository.getUserByUserId(userId).getGallery().getId();
    }

    public long getPetCheckGalleryId(long petCheckId) {
        return petCheckRepository.getOne(petCheckId).getGallery().getId();
    }

    public boolean deleteGallery(long galleryId) {
        galleryRepository.delete(galleryRepository.getOne(galleryId));
        return true;
    }
}
