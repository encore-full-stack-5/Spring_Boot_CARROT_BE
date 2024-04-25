package com.example.carrot_market.area.db;

import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.UserArea;
import com.example.carrot_market.area.service.UpdateUserAreaRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AreaMapper {

    Area selectAreaById(int areaId);
    boolean validateArea(int areaId, int userId);
    List<Area> selectAreasByLatLonAndRange(
            @Param("lat") double lat,
            @Param("lon") double lon,
            @Param("distance") int distance
    );
    void insertAreaToUser(
            @Param("areaId") int areaId,
            @Param("userId") int userId,
            @Param("areaRange") int areaRange,
            @Param("isDefault") boolean isDefault
    );
    // 사용자에게 설정된 지역 수정(업데이트)
    void updateUserArea(UpdateUserAreaRequestDto updateData);
    void updateUserOtherArea(UpdateUserAreaRequestDto updateData);

    List<Area> selectAreasByUserId(int userId);

    // 특정 지역 ID가 사용자의 현재 설정된 지역에 가까운지 검증
    boolean validateAreaToUserDefault(int productAreaId, int currentRange, int areaId);

    void deleteAreaToUser (int areaId, int userId);

    // 사용자에게 설정된 지역 목록
    Optional<Area> getAreaName(@Param("areaId")int areaId);
    List<UserArea> getAreaListByUserId(int userId);
    // 사용자에게 기본으로 설정된 지역 목록
    UserArea getDefaultAreaByUserId(int userId);
}
