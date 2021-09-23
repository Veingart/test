package com.turchanova.testProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность галереи")
public class GalleryDto {
    @Schema(description = "Идентификатор")
    long id;
    @Schema(description = "Название галереи")
    String name;
    @Schema(description = "Адрес галереи")
    AddressDto addressDto;
}
