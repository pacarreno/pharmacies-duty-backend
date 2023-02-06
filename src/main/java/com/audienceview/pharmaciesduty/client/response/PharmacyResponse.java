package com.audienceview.pharmaciesduty.client.response;

import com.audienceview.pharmaciesduty.config.FlexibleDoubleDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Pharmacy response from the API
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PharmacyResponse {

    private String fecha;
    @JsonProperty("local_id")
    private int storeId;
    @JsonProperty("fk_region")
    private int fkRegion;
    @JsonProperty("fk_comuna")
    private int fkComuna;
    @JsonProperty("fk_localidad")
    private int fkLocalidad;
    @JsonProperty("local_nombre")
    private String storeName;
    @JsonProperty("comuna_nombre")
    private String communeName;
    @JsonProperty("localidad_nombre")
    private String locationName;
    @JsonProperty("local_direccion")
    private String storeAddress;
    @JsonProperty("funcionamiento_hora_apertura")
    private String openTime;
    @JsonProperty("funcionamiento_hora_cierre")
    private String closeTime;
    @JsonProperty("local_telefono")
    private String storePhone;
    @JsonProperty("local_lat")
    @JsonDeserialize(using = FlexibleDoubleDeserializer.class)
    private double storeLatitude;
    @JsonProperty("local_lng")
    @JsonDeserialize(using = FlexibleDoubleDeserializer.class)
    private double storeLongitude;
    @JsonProperty("funcionamiento_dia")
    private String workingDay;

}
