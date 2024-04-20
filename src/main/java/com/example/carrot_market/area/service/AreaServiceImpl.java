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

    // 지역 정보를 반환
    @Override
    public Area selectAreaById(int areaId) {
        return areaMapper.selectAreaById(areaId);
    }

    // 특정 지역 ID가 사용자의 현재 설정된 지역에 가까운지 검증
    @Override
    public boolean validateAreaToUserDefault(int productAreaId, int currentRange, int areaId) {
        return areaMapper.validateAreaToUserDefault(productAreaId, currentRange, areaId);
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

    // 사용자에게 설정된 지역 목록
    @Override
    public List<Area> getAreaListByUserId(int userId) {
        return areaMapper.getAreaListByUserId(userId);
    }

    @Override
    public Boolean deleteAreaToUser(int areaId, int userId) {
        return null;
    }


}
