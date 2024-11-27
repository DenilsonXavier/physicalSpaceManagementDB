package com.ucsal.physicalSpaceManagement.equipment;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucsal.physicalSpaceManagement.equipment.dto.EquipmentDTO;
import com.ucsal.physicalSpaceManagement.equipment.entities.Equipment;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Equipment createEquipment(EquipmentDTO equipmentDTO) {
        Equipment equipment = modelMapper.map(equipmentDTO, Equipment.class);
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }
}
