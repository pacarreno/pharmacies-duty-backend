package com.audienceview.pharmaciesduty.service;

import com.audienceview.pharmaciesduty.client.PharmacyOnDutyClient;
import com.audienceview.pharmaciesduty.client.response.PharmacyResponse;
import com.audienceview.pharmaciesduty.domain.Pharmacy;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class PharmacyServiceTest {

    @MockBean
    PharmacyOnDutyClient pharmacyOnDutyClient;

    @Autowired
    PharmacyService pharmacyService;

    private Gson gson = new Gson();


    @Test
    void find_without_params() throws JSONException {

        final String storeName = "CRUZ VERDE";
        final Integer fkCommune = 56;

        when(pharmacyOnDutyClient.getAll())
                .thenReturn(createPharmaciesList());

        List<Pharmacy> pharmacyList = pharmacyService.getPharmaciesOnDuty(null, null);

        assertEquals(3,pharmacyList.size());
        assertEquals(storeName, pharmacyList.get(0).getName());
        assertEquals(String.valueOf(fkCommune), pharmacyList.get(0).getAddress().getCommune());

    }

    @Test
    void find_pharmacy_cruz_verde() throws JSONException {

        final String storeName = "CRUZ VERDE";
        final Integer fkCommune = 56;

        when(pharmacyOnDutyClient.getAll())
                .thenReturn(createPharmaciesList());

        List<Pharmacy> pharmacyList = pharmacyService.getPharmaciesOnDuty(storeName, fkCommune);

        assertEquals(1,pharmacyList.size());
        assertEquals(storeName, pharmacyList.get(0).getName());
        assertEquals(String.valueOf(fkCommune), pharmacyList.get(0).getAddress().getCommune());

    }

    private List<PharmacyResponse> createPharmaciesList() {
        return List.of(
                createCruzVerdePharmacyResponse(),
                createSalcobranPharmacyResponse(),
                createEcofarmaciasPharmacyResponse());
    }

    private PharmacyResponse createCruzVerdePharmacyResponse() {
        return createPharmacyResponse("CRUZ VERDE");
    }
    private PharmacyResponse createSalcobranPharmacyResponse() {
        return createPharmacyResponse("SALCOBRAND");
    }
    private PharmacyResponse createEcofarmaciasPharmacyResponse() {
        return createPharmacyResponse("ECOFARMACIAS");
    }

    private PharmacyResponse createPharmacyResponse(String storeName) {
        return PharmacyResponse.builder()
                .storeName(storeName)
                .locationName("street")
                .fkComuna(56)
                .communeName("56")
                .fkRegion(5)
                .build();
    }


}