package com.ucsal.physicalSpaceManagement.physicalSpaceEquipment.entities;

import com.ucsal.physicalSpaceManagement.equipment.entities.Equipment;
import com.ucsal.physicalSpaceManagement.physicalSpace.entities.PhysicalSpace;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PHYSICAL_SPACE_EQUIPMENT")
@Data
public class PhysicalSpaceEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "space_id", referencedColumnName = "space_id", nullable = false)
    private PhysicalSpace space;

    @ManyToOne
    @JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id", nullable = false)
    private Equipment equipment;
}
