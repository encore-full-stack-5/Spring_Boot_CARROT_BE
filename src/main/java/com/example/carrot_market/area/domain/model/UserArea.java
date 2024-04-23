package com.example.carrot_market.area.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UserArea {

//    List<Area> areas;
//    AreaRange areaRange;

    private int userId;
    private int areaId;
    private String do_city;
    private String si_gu;
    private String dong;
    private String eup;
    private String ri;
    private double latitude;
    private double longitude;
    private int isDefault;
    private int areaRange;

}
