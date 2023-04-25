package com.excel.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Country {
    private String countryName;
    private String icuCode;
    private String isoCode;

    public Country() {
    }

    public Country(String countryName, String icuCode, String isoCode) {
        this.countryName = countryName;
        this.icuCode = icuCode;
        this.isoCode = isoCode;
    }
}
