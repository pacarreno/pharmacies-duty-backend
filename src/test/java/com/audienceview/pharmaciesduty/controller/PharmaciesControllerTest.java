package com.audienceview.pharmaciesduty.controller;

import com.audienceview.pharmaciesduty.PharmaciesDutyApplication;
import com.audienceview.pharmaciesduty.controller.dto.PharmacyDTO;
import com.audienceview.pharmaciesduty.domain.Address;
import com.audienceview.pharmaciesduty.domain.Pharmacy;
import com.audienceview.pharmaciesduty.service.PharmacyService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PharmaciesDutyApplication.class)
@Slf4j
class PharmaciesControllerTest {

    final String baseURL = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PharmacyService pharmacyService;

    private Gson gson = new Gson();

    @Test
    void find_without_params() throws JSONException {

        final String storeName = "CRUZ VERDE";
        final Integer fkCommune = 56;

        when(pharmacyService.getPharmaciesOnDuty(null, null))
                .thenReturn(createPharmaciesList());

        ResponseEntity<String> responseEntityExpedient =
                restTemplate.getForEntity(baseURL+port+"/pharmacies",  String.class);

        String body = responseEntityExpedient.getBody();

        assertEquals(HttpStatus.OK,responseEntityExpedient.getStatusCode());
        assertNotNull(body);

        PharmacyDTO[] convertedObject = gson.fromJson(body, PharmacyDTO[].class);

        assertEquals(3,convertedObject.length);
        assertEquals(storeName, convertedObject[0].getName());
        assertTrue(convertedObject[0].getAddress().indexOf(String.valueOf(fkCommune)) > -1);

    }

    @Test
    void find_pharmacy_cruz_verde() throws JSONException {

        final String storeName = "CRUZ VERDE";
        final Integer fkCommune = 56;

        when(pharmacyService.getPharmaciesOnDuty(storeName, fkCommune))
                .thenReturn(List.of(createPharmaciesList().get(0)));

        Map<String, String> vars = new HashMap<>();
        vars.put("storeName", storeName);
        vars.put("commune", String.valueOf(fkCommune));

        ResponseEntity<String> responseEntityExpedient =
                restTemplate.getForEntity(baseURL+port+"/pharmacies?storeName={storeName}&commune={commune}",  String.class, vars);

        String body = responseEntityExpedient.getBody();

        assertEquals(HttpStatus.OK,responseEntityExpedient.getStatusCode());
        assertNotNull(body);

        PharmacyDTO[] convertedObject = gson.fromJson(body, PharmacyDTO[].class);

        assertEquals(1,convertedObject.length);
        assertEquals(storeName, convertedObject[0].getName());
        assertTrue(convertedObject[0].getAddress().indexOf(String.valueOf(fkCommune)) > -1);

    }

    private List<Pharmacy> createPharmaciesList() {
        return List.of(
                createCruzVerdePharmacy(),
                createSalcobranPharmacy(),
                createEcofarmaciasPharmacy());
    }

    private Pharmacy createCruzVerdePharmacy() {
        return createPharmacy("CRUZ VERDE");
    }
    private Pharmacy createSalcobranPharmacy() {
        return createPharmacy("SALCOBRAND");
    }
    private Pharmacy createEcofarmaciasPharmacy() {
        return createPharmacy("ECOFARMACIAS");
    }

    private Pharmacy createPharmacy(String storeName) {
        return Pharmacy.builder()
                .name(storeName)
                .address(Address.builder().street("street").commune("56").region("5").build())
                .build();
    }


}