package com.turchanova.testProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность картины")
public class PaintingDto {
    @Schema(description = "Идентификатор")
    long id;
    @Schema(description = "Название картины")
    String title;
    @Schema(description = "Год создания картины")
    int year;
    @Schema(description = "Галерея, в которой находится картина")
    GalleryDto galleryDto;
    @Schema(description = "Список художников-авторов")
    List<AuthorDto> authorDtoList;
}
