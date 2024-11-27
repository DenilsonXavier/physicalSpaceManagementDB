package com.ucsal.physicalSpaceManagement.equipment.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "EQUIPMENT")
@Data
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long equipmentId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
