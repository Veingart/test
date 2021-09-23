package com.turchanova.testProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Форма для добавления информации о картине")
public class PaintingForm {
    @Schema(description = "Название картины")
    String title;
    @Schema(description = "Год создания картины")
    int year;
    @Schema(description = "Список художников-авторов")
    List<AuthorForm> authorFormList;
    @Schema(description = "Идентификатор галереи")
    long gallery_id;

}
