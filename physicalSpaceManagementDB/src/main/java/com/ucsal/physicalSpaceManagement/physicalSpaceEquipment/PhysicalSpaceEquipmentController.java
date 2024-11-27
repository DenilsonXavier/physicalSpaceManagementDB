package com.ucsal.physicalSpaceManagement.physicalSpaceEquipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ucsal.physicalSpaceManagement.physicalSpaceEquipment.dto.PhysicalSpaceEquipmentDTO;
import com.ucsal.physicalSpaceManagement.physicalSpaceEquipment.entities.PhysicalSpaceEquipment;

@RestController
@RequestMapping("/physical-space-equipment")
public class PhysicalSpaceEquipmentController {

    @Autowired
    private PhysicalSpaceEquipmentService physicalSpaceEquipmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PhysicalSpaceEquipment addEquipmentToSpace(@RequestBody PhysicalSpaceEquipmentDTO dto) {
        return physicalSpaceEquipmentService.addEquipmentToSpace(dto.getSpaceId(), dto.getEquipmentId());
    }
}
