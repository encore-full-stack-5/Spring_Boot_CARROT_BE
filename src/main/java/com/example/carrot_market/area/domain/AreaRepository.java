package com.example.carrot_market.area.domain;

import com.example.carrot_market.area.domain.model.Area;

import java.util.List;

interface AreaRepository {
    boolean validateArea(int areaId, int userId);
    List<Area> selectAreasByLatLonAndRange(double lat, double lon, int distance);
    Area updateDefaultArea(int areaId, int userId);

}
