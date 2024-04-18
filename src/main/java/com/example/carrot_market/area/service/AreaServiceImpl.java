package com.example.carrot_market.area.service;

import com.example.carrot_market.area.db.AreaMapper;
import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.AreaRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private final AreaMapper areaMapper;
    public AreaServiceImpl(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    @Override
    public Area selectAreaById(int areaId) {
        return areaMapper.selectAreaById(areaId);
    }

    @Override
    public boolean validateAreaToUserDefault(int targetAreaId, int userID) {
        return false;
    }

    @Override
    public List<Area> getAreaToLatLon(double lat, double lon, AreaRange areaRange) {
        return areaMapper.selectAreasByLatLonAndRange(lat, lon, areaRange.getDistance());
    }

    @Override
    public Area changeDefaultArea(int areaId, int userID) {
        return null;
    }

    @Override
    public void addAreaToUser(int areaId, int userID, int areaRange, boolean isDefault) {
        areaMapper.insertAreaToUser(areaId, userID, areaRange, isDefault);
    }

    @Override
    public List<Area> getAreaListByUserId(int userID) {
        return null;
    }
}
