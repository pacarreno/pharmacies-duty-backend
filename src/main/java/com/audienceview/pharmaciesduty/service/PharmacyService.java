package com.audienceview.pharmaciesduty.service;

import com.audienceview.pharmaciesduty.client.PharmacyOnDutyClient;
import com.audienceview.pharmaciesduty.client.response.PharmacyResponse;
import com.audienceview.pharmaciesduty.domain.Address;
import com.audienceview.pharmaciesduty.domain.Pharmacy;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Pharmacy Service
 */
@AllArgsConstructor
@Service
public class PharmacyService {

    private PharmacyOnDutyClient pharmacyOnDutyClient;

    public List<Pharmacy> getPharmaciesOnDuty(String storeName, Integer commune) {
        return pharmacyOnDutyClient.getAll().stream().filter(pharmacy -> filterByStoreNameAndCommune(pharmacy, storeName, commune)).map(this::convertToModel).collect(Collectors.toList());
    }

    private Pharmacy convertToModel(PharmacyResponse pharmacyResponse) {
        return Pharmacy.builder().name(pharmacyResponse.getStoreName()).address(buildAddress(pharmacyResponse)).longitude(pharmacyResponse.getStoreLongitude()).latitude(pharmacyResponse.getStoreLatitude()).telephone(pharmacyResponse.getStorePhone()).build();
    }

    private Address buildAddress(PharmacyResponse pharmacyResponse) {
        return Address.builder().street(pharmacyResponse.getStoreAddress()).region(String.valueOf(pharmacyResponse.getFkRegion())).commune(pharmacyResponse.getCommuneName()).build();
    }

    private boolean filterByStoreNameAndCommune(PharmacyResponse pharmacyResponse, String storeName, Integer commune) {
        return filterByStoreName(pharmacyResponse, storeName) && filterByCommune(pharmacyResponse, commune);
    }

    private static boolean filterByStoreName(PharmacyResponse pharmacyResponse, String storeName) {
        return Strings.isBlank(storeName) || pharmacyResponse.getStoreName().compareTo(storeName) == 0;
    }

    private static boolean filterByCommune(PharmacyResponse pharmacyResponse, Integer commune) {
        return commune == null || pharmacyResponse.getFkComuna() == commune;
    }
}
