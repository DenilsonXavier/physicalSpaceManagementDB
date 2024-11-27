package com.ucsal.physicalSpaceManagement.physicalSpace;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ucsal.physicalSpaceManagement.physicalSpace.dto.PhysicalSpaceDTO;
import com.ucsal.physicalSpaceManagement.physicalSpace.entities.PhysicalSpace;
import com.ucsal.physicalSpaceManagement.physicalSpace.enums.PhysicalSpaceType;

@RestController
@RequestMapping("/physical-spaces")
public class PhysicalSpaceController {

    @Autowired
    private PhysicalSpaceService physicalSpaceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PhysicalSpace createPhysicalSpace(@RequestBody @Valid PhysicalSpaceDTO physicalSpaceDTO) {
        return physicalSpaceService.createPhysicalSpace(physicalSpaceDTO);
    }

    @GetMapping
    public List<PhysicalSpace> getAllPhysicalSpaces(
            @RequestParam(required = false) List<PhysicalSpaceType> types) {
        return physicalSpaceService.getAllPhysicalSpaces(types);
    }
}
