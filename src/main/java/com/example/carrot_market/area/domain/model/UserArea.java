package com.example.carrot_market.area.domain.model;

import com.example.carrot_market.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UserArea {

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

    public UserArea(User user, Area area, int areaRange) {
        this.userId = user.getId();
        this.areaId = area.getId();
        this.do_city = area.getDo_city();
        this.si_gu = area.getSi_gu();
        this.dong = area.getDong();
        this.eup = area.getEup();
        this.ri = area.getRi();
        this.latitude = area.getLatitude();
        this.longitude = area.getLongitude();
        this.isDefault = 1;
        this.areaRange = areaRange;
    }
}
