package com.petvacay.mappers.media;


import com.petvacay.dto.media.MediaTypeDto;
import com.petvacay.entities.MediaType;
import com.petvacay.mappers.GeneralMapper;

public class MediaTypeMapper implements GeneralMapper<MediaType, MediaTypeDto> {
    @Override
    public MediaTypeDto convertToDto(MediaType model) {
        return MediaTypeDto.builder()
                .id(model.getId())
                .type(model.getType())
                .build();
    }

    @Override
    public MediaType convertToModel(MediaTypeDto dto) {
        return MediaType.builder()
                .id(dto.getId())
                .type(dto.getType())
                .build();
    }
}
