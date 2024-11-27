package com.ucsal.physicalSpaceManagement.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucsal.physicalSpaceManagement.equipment.entities.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
