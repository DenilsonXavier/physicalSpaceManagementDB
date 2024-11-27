package com.ucsal.physicalSpaceManagement.physicalSpace.entities;

import lombok.Data;

import java.io.Serializable;

import com.ucsal.physicalSpaceManagement.physicalSpace.enums.PhysicalSpaceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PHYSICAL_SPACE")
@Data
public class PhysicalSpace implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "space_id")
    private Long spaceId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "capacity")
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PhysicalSpaceType type;

    @Column(name = "location", length = 255)
    private String location;
}
