package com.audienceview.pharmaciesduty.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class FlexibleDoubleDeserializer extends JsonDeserializer<Double> {

    @Override
    public Double deserialize(JsonParser parser, DeserializationContext context)
            throws IOException {
        String doubleString = parser.getText();
        if (Strings.isBlank(doubleString)) {
            return 0d;
        }

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        try {
            return numberFormat.parse(doubleString).doubleValue();
        } catch (ParseException e) {
            log.error("Error parse number {}", doubleString, e);
            return 0d;
        }

    }

}
