package com.example.carrot_market.area.service;

import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.area.domain.model.UserArea;

import java.util.List;

// 이미지 업로드
// 채팅구현

public interface AreaService {

   
    /**
     * @param areaId
     * @return Area
     *
     * 지역 ID를 받아서 지역정보를 반환
     */
    Area selectAreaById(int areaId);

    
    /**
     * @param productAreaId 상품 지역 ID
     * @param currentRange 지역 범위
     * @param areaId 유저 지역 ID
     * @return boolean
     * SELECT * FROM areas
     *         WHERE ST_Distance_Sphere(
     *         productAreaId.geo_point,
     *         areaId.geo_point)
     *         ) &lt;= #{currentRange*800}
     * 특정 지역 ID가 사용자의 현재 설정된 지역에 가까운지 검증한다.
     */
    boolean validateAreaToUserDefault(int productAreaId, int currentRange, int areaId);

    
    /**
     * @param lat
     * @param lon
     * @param areaRange
     * @return List<Area>
     *
     * 위도, 경도, 지역 범위를 받아서 해당 범위 내의 지역 정보를 반환한다.
     */
    List<Area> getAreaToLatLon(double lat, double lon, AreaRange areaRange);


    /**
     * @return Area
     * <p>
     * user_area 테이블 정보 수정
     */
    UpdateUserAreaRequestDto updateUserArea(
            UpdateUserAreaRequestDto updateUserAreaRequestDto,
            int orgAreaId
    );

    
    /**
     *
     * @param areaId
     * @param userId
     * @param areaRange 좁은범위 1 중간범위 2 넒은범위 3
     *
     * 추가되는 지역정보는 기본적으로 default 설정이됨 따라서 기존 디폴트 지역 업데이트 필요
     * 지역 ID, 사용자 ID, 지역 범위, 기본 지역 여부를 받아서 user_area table 데이터 추가.
     */
    void addAreaToUser(int areaId, int userId, int areaRange);
   
   
    /**
     * @param userId
     * @return List<UserArea>
     *
     * 사용자 ID를 받아서 사용자에게 설정된 지역목록을 불러온다.
     */
    List<UserArea> getAreaListByUserId(int userId);

    /**
     * @param userId
     * @return UserArea
     *
     * 사용자 ID를 받아서 사용자에게 기본으로 설정된 지역을 불러온다.
     */
    UserArea getDefaultAreaByUserId(int userId);

    /**
     * @param areaId
     * @param userId
     * @return
     *
     * 지역 ID와 유저 ID를 받아서 user_area 테이블 row를 삭제한다.
     */
    void deleteAreaToUser(int areaId, int userId);
}
