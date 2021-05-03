package com.petvacay.dto.performer;

import com.petvacay.dto.category.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformerPreviewDTO {

    protected long userId;

    protected String firstName;

    private String city;

    private double pricePerDay;

    private List<CategoryDTO> categories;
}
