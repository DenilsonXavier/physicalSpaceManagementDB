package com.ucsal.physicalSpaceManagement.physicalSpace;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucsal.physicalSpaceManagement.physicalSpace.dto.PhysicalSpaceDTO;
import com.ucsal.physicalSpaceManagement.physicalSpace.entities.PhysicalSpace;
import com.ucsal.physicalSpaceManagement.physicalSpace.enums.PhysicalSpaceType;

@Service
public class PhysicalSpaceService {

    @Autowired
    private PhysicalSpaceRepository physicalSpaceRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PhysicalSpace createPhysicalSpace(PhysicalSpaceDTO physicalSpaceDTO) {
        PhysicalSpace physicalSpace = modelMapper.map(physicalSpaceDTO, PhysicalSpace.class);

        return physicalSpaceRepository.save(physicalSpace);
    }

    public List<PhysicalSpace> getAllPhysicalSpaces(List<PhysicalSpaceType> types) {
        if (types == null || types.isEmpty()) {
            return physicalSpaceRepository.findAll();
        } else {
            return physicalSpaceRepository.findByTypeIn(types);
        }
    }
}
