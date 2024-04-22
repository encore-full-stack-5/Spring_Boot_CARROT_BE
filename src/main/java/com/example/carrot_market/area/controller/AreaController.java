package com.example.carrot_market.area.controller;

import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.area.dto.AddAreaRequestDto;
import com.example.carrot_market.area.service.AreaService;
import com.example.carrot_market.area.service.UpdateUserAreaRequestDto;
import com.example.carrot_market.core.base.BaseResponseEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/areas")
public class AreaController {
    @Autowired
    private final AreaService areaService;
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    // 특정 지역 ID가 사용자의 현재 설정된 지역에 가까운지 검증
    @GetMapping("/validate")
    public boolean validateAreaToUserDefault(
            @RequestParam("areaId") int targetAreaId,
            @RequestParam("currentRange") int currentRange,
            @RequestParam("userId") int userId
    ) {
        return areaService.validateAreaToUserDefault(targetAreaId, currentRange, userId);
    }

    // 지역 ID를 받아서 지역정보를 반환
    @GetMapping("/{id}")
    public Area selectAreaById(@PathVariable("id") int areaId) {
        return areaService.selectAreaById(areaId);
    }

    @GetMapping("/search")
    public List<Area> getAreaToLatLon(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam("range") int areaRange
    ) {
        return areaService.getAreaToLatLon(lat, lon, AreaRange.convertIDToAreaRange(areaRange));
    }

    //사용자에게 설정된 지역 목록
    @GetMapping("/userAreas/{id}")
    public List<Area> getAreaListByUserId(@PathVariable("id") int userId) {
        return areaService.getAreaListByUserId(userId);
    }

    @PutMapping("/default")
    public Area changeDefaultArea(@RequestBody UpdateUserAreaRequestDto updateUserAreaRequestDto) {
        return areaService.updateUserArea(updateUserAreaRequestDto);
    }

    @PostMapping
    public ResponseEntity<BaseResponseEntity<?>> addAreaToUser(@Valid @RequestBody AddAreaRequestDto addAreaRequestDto) {
        areaService.addAreaToUser(
                addAreaRequestDto.getAreaId(),
                addAreaRequestDto.getUserId(),
                addAreaRequestDto.getAreaRange()
        );
        return BaseResponseEntity.ok("success");
    }
}
