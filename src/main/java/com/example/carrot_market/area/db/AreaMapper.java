package com.example.carrot_market.area.db;

import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.UserArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
            @Param("rangeArea") int rangeArea,
            @Param("isDefault") boolean isDefault
    );
    Area updateDefaultArea(int areaId, int userId);
    List<Area> selectAreasByUserId(int userId);

    // 특정 지역 ID가 사용자의 현재 설정된 지역에 가까운지 검증
    boolean validateAreaToUserDefault(int productAreaId, int currentRange, int areaId);

    void deleteAreaToUser (int areaId, int userId);


    // 사용자에게 설정된 지역 목록
    List<Area> getAreaListByUserId(int userId);
}
