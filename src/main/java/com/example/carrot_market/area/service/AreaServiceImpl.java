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
    public boolean validateAreaToUserDefault(int productAreaId, int currentRange, int areaId) {
        return false;
    }

    @Override
    public List<Area> getAreaToLatLon(double lat, double lon, AreaRange areaRange) {
        return areaMapper.selectAreasByLatLonAndRange(lat, lon, areaRange.getDistance());
    }

    @Override
    public Area updateUserArea(UpdateUserAreaRequestDto updateUserAreaRequestDto) {
        return null;
    }

    @Override
    public void addAreaToUser(int areaId, int userId, int areaRange) {
        areaMapper.insertAreaToUser(areaId, userId, areaRange, true);
    }

    @Override
    public List<Area> getAreaListByUserId(int userId) {
        return null;
    }

    @Override
    public Boolean deleteAreaToUser(int areaId, int userId) {
        return null;
    }


}
