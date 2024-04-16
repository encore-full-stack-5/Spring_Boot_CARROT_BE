package com.example.carrot_market.area.service;

import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.AreaRange;

import java.util.List;

public interface AreaService {

    Area selectAreaById(int areaId);
    boolean validateAreaToUserDefault(int targetAreaId, int userID);
    List<Area> getAreaToLatLon(double lat, double lon, AreaRange areaRange);
    Area changeDefaultArea(int areaId, int userID);
    void addAreaToUser(int areaId, int userID, int areaRange, boolean isDefault);
    List<Area> getAreaListByUserId(int userID);

}
