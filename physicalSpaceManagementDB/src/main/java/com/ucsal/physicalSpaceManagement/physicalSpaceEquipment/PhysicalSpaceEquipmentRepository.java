package com.ucsal.physicalSpaceManagement.physicalSpaceEquipment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ucsal.physicalSpaceManagement.physicalSpaceEquipment.entities.PhysicalSpaceEquipment;

@Repository
public interface PhysicalSpaceEquipmentRepository extends JpaRepository<PhysicalSpaceEquipment, Long> {

    @Query("SELECT pse FROM PhysicalSpaceEquipment pse WHERE pse.space.spaceId = :spaceId AND pse.equipment.equipmentId = :equipmentId")
    Optional<PhysicalSpaceEquipment> findBySpaceIdAndEquipmentId(Long spaceId, Long equipmentId);
}
