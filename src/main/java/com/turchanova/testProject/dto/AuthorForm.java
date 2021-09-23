package com.turchanova.testProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Форма для добавления информации о художнике")
public class AuthorForm {
    @Schema(description = "Имя художника")
    String name;
    @Schema(description = "Фамилия художника")
    String surname;
}
