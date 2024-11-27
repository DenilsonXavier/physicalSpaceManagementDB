package com.ucsal.physicalSpaceManagement.physicalSpace;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucsal.physicalSpaceManagement.physicalSpace.entities.PhysicalSpace;
import com.ucsal.physicalSpaceManagement.physicalSpace.enums.PhysicalSpaceType;

@Repository
public interface PhysicalSpaceRepository extends JpaRepository<PhysicalSpace, Long> {
    List<PhysicalSpace> findByTypeIn(List<PhysicalSpaceType> types);

}
