package com.audienceview.pharmaciesduty.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String street;
    private String commune;
    private String region;
}
