package com.ucsal.physicalSpaceManagement.physicalSpaceEquipment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucsal.physicalSpaceManagement.equipment.EquipmentRepository;
import com.ucsal.physicalSpaceManagement.equipment.entities.Equipment;
import com.ucsal.physicalSpaceManagement.physicalSpace.PhysicalSpaceRepository;
import com.ucsal.physicalSpaceManagement.physicalSpace.entities.PhysicalSpace;
import com.ucsal.physicalSpaceManagement.physicalSpaceEquipment.entities.PhysicalSpaceEquipment;

@Service
public class PhysicalSpaceEquipmentService {

    @Autowired
    private PhysicalSpaceEquipmentRepository physicalSpaceEquipmentRepository;

    @Autowired
    private PhysicalSpaceRepository physicalSpaceRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    public PhysicalSpaceEquipment addEquipmentToSpace(Long spaceId, Long equipmentId) {
        Optional<PhysicalSpace> physicalSpace = physicalSpaceRepository.findById(spaceId);
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);

        if (!physicalSpace.isPresent()) {
            throw new IllegalArgumentException("Espaço não encontrado");
        }

        if (!equipment.isPresent()) {
            throw new IllegalArgumentException("Equipamento não encontrado");
        }

        Optional<PhysicalSpaceEquipment> existingAssociation = physicalSpaceEquipmentRepository
                .findBySpaceIdAndEquipmentId(spaceId, equipmentId);

        if (existingAssociation.isPresent()) {
            throw new IllegalArgumentException("Este equipamento já está associado a este espaço");
        }

        PhysicalSpaceEquipment physicalSpaceEquipment = new PhysicalSpaceEquipment();
        physicalSpaceEquipment.setSpace(physicalSpace.get());
        physicalSpaceEquipment.setEquipment(equipment.get());

        return physicalSpaceEquipmentRepository.save(physicalSpaceEquipment);
    }
}
