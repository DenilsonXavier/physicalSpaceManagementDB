package com.ucsal.physicalSpaceManagement.physicalSpace.dto;

import com.ucsal.physicalSpaceManagement.physicalSpace.enums.PhysicalSpaceType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PhysicalSpaceDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    private String description;

    @NotNull(message = "A capacidade é obrigatória")
    private Integer capacity;

    @NotNull(message = "O tipo de espaço é obrigatório")
    private PhysicalSpaceType type;

    private String location;
}
