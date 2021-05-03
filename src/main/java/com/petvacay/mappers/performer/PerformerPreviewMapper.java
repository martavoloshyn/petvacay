package com.petvacay.mappers.performer;

import com.petvacay.dto.performer.PerformerPreviewDTO;
import com.petvacay.entities.Performer;
import com.petvacay.mappers.GeneralMapper;
import com.petvacay.mappers.category.CategoryMapper;
import com.petvacay.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PerformerPreviewMapper implements GeneralMapper<Performer, PerformerPreviewDTO> {

    private PricingService pricingService;
    private CategoryMapper categoryMapper;

    @Autowired
    public PerformerPreviewMapper(PricingService pricingService, CategoryMapper categoryMapper) {
        this.pricingService = pricingService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public PerformerPreviewDTO convertToDto(Performer model) {
        return PerformerPreviewDTO.builder()
                .categories(categoryMapper.convertListToDto(model.getCategories()))
                .city(model.getCity())
                .firstName(model.getFirstName())
                .userId(model.getUserId())
                .pricePerDay(pricingService.calculatePricePerDay(model.getPricing()))
                .build();
    }

    @Override
    public Performer convertToModel(PerformerPreviewDTO dto) {
        return null;
    }

    public List<PerformerPreviewDTO> convertListToDto(List<Performer> models) {
        List<PerformerPreviewDTO> dtos = new ArrayList<>();
        models.forEach(model -> dtos.add(convertToDto(model)));
        return dtos;
    }
}
