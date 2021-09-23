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
@Schema(description = "Сущность художника")
public class AuthorDto {
    @Schema(description = "Идентификатор")
    long id;
    @Schema(description = "Имя художника")
    String name;
    @Schema(description = "Фамилия художника")
    String surname;
}
