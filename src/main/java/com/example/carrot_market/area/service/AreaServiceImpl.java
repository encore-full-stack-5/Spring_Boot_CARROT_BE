package com.example.carrot_market.area.service;

import com.example.carrot_market.area.db.AreaMapper;
import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.area.domain.model.UserArea;
import com.example.carrot_market.core.error.CommonError;
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
    public UpdateUserAreaRequestDto updateUserArea(
            UpdateUserAreaRequestDto updateUserAreaRequestDto,
            int orgAreaId
    ) {
        List<UserArea> userById = areaMapper.getAreaListByUserId(updateUserAreaRequestDto.getUserId());
        if(userById.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist user");
        // 다른 areaId
        int otherAreaId = 0;
        for(UserArea userArea : userById) {
            if(userArea.getAreaId() != orgAreaId) {
                otherAreaId = userArea.getAreaId();
            }
        }

        UpdateUserAreaRequestDto updateData = UpdateUserAreaRequestDto.builder()
                .userId(updateUserAreaRequestDto.getUserId())
                .areaId(updateUserAreaRequestDto.getAreaId())
                .areaRange(updateUserAreaRequestDto.getAreaRange())
                .isDefault(updateUserAreaRequestDto.getIsDefault())
                .orgAreaId(orgAreaId)
                .otherAreaId(otherAreaId)
                .build();
        areaMapper.updateUserArea(updateData);
        areaMapper.updateUserOtherArea(updateData);
        return updateData;
    }

    @Override
    public void addAreaToUser(int areaId, int userId, int areaRange) {
        areaMapper.insertAreaToUser(areaId, userId, areaRange, true);
    }

    // 사용자에게 설정된 지역 목록
    @Override
    public List<UserArea> getAreaListByUserId(int userId) {
        return areaMapper.getAreaListByUserId(userId);
    }

    // 사용자에게 기본으로 설정된 지역 목록
    public UserArea getDefaultAreaByUserId(int userId) {
        return areaMapper.getDefaultAreaByUserId(userId);
    }

    @Override
    public void deleteAreaToUser(int areaId, int userId) {
        areaMapper.deleteAreaToUser(areaId, userId);
    }
}
