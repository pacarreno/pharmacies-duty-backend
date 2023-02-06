package com.audienceview.pharmaciesduty.client;

import com.audienceview.pharmaciesduty.client.response.PharmacyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PharmacyOnDutyClient {

    @Value("${pharmacies_endpoint}")
    private String serviceEndpoint;
    private final RestTemplate restTemplate;

    /**
     * Get all pharmacies for a particular commune
     *
     * @return The list of pharmacies on duty to a particular commune
     */
    public List<PharmacyResponse> getAll() {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        final HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 Firefox/26.0");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);


        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<List<PharmacyResponse>> response =
                restTemplate.exchange(serviceEndpoint,
                        HttpMethod.GET,
                        entity,
                new ParameterizedTypeReference<List<PharmacyResponse>>() {
                });

        return response.getBody();
    }
}
