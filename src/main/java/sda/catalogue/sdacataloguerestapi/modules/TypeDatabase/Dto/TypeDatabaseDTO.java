package sda.catalogue.sdacataloguerestapi.modules.TypeDatabase.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
public class TypeDatabaseDTO {

    @NotEmpty
    @NotNull
    private String typeDatabase;
}
