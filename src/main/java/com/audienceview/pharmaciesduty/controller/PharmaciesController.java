package com.audienceview.pharmaciesduty.controller;

import com.audienceview.pharmaciesduty.controller.dto.PharmacyDTO;
import com.audienceview.pharmaciesduty.domain.Pharmacy;
import com.audienceview.pharmaciesduty.service.PharmacyService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("pharmacies")
public class PharmaciesController {

    private PharmacyService pharmacyService;

    private ModelMapper modelMapper;


    @GetMapping(produces = "application/json" )
    public List<PharmacyDTO> filter(@RequestParam(required = false) String storeName,
                                    @RequestParam(required = false) Integer commune){
        return pharmacyService
                .getPharmaciesOnDuty(storeName, commune)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PharmacyDTO convertToDto(Pharmacy pharmacy) {


        PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);
        if (pharmacy.getAddress() != null) {
            pharmacyDTO.setAddress(
                    Strings.join(
                            List.of(pharmacy.getAddress().getStreet(),
                                    pharmacy.getAddress().getCommune(),
                                    pharmacy.getAddress().getRegion()),
                            ' '
                    ));
        }

        return pharmacyDTO;
    }

}
