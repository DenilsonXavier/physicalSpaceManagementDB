package com.ucsal.physicalSpaceManagement.equipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ucsal.physicalSpaceManagement.equipment.dto.EquipmentDTO;
import com.ucsal.physicalSpaceManagement.equipment.entities.Equipment;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment createEquipment(@RequestBody @Valid EquipmentDTO equipmentDTO) {
        return equipmentService.createEquipment(equipmentDTO);
    }

    @GetMapping
    public List<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }
}
