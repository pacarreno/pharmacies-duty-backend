package com.audienceview.pharmaciesduty.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PharmacyDTO {

    private String name;

    private String address;

    private String telephone;

    private double latitude;

    private double longitude;
}
