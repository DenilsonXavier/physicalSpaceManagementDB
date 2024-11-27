package com.ucsal.physicalSpaceManagement.equipment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EquipmentDTO {

    @NotBlank(message = "O nome do equipamento é obrigatório")
    private String name;

    private String description;
}
