package com.audienceview.pharmaciesduty.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pharmacy {

    private String name;
    private Address address;
    private String telephone;
    private double latitude;
    private double longitude;

}
