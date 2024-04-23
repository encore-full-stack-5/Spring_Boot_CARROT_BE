package com.example.carrot_market.area.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Area {

    private int id;
    private String do_city;
    private String si_gu;
    private String dong;
    private String eup;
    private String ri;
    private double latitude;
    private double longitude;
}
