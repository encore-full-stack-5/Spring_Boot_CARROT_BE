package com.example.carrot_market.area.controller;

import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.area.dto.AddAreaRequestDto;
import com.example.carrot_market.area.service.AreaService;
import com.example.carrot_market.core.BaseResponseEntity;
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

    @GetMapping("/validate")
    public boolean validateAreaToUserDefault(
            @RequestParam("areaID") int targetAreaId,
            @RequestParam("userId") int userID
    ) {
        return areaService.validateAreaToUserDefault(targetAreaId, userID);
    }

    @GetMapping("/search")
    public List<Area> getAreaToLatLon(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam("range") int areaRange
    ) {
        return areaService.getAreaToLatLon(lat, lon, AreaRange.convertIDToAreaRange(areaRange));
    }

    @PutMapping("/default")
    public Area changeDefaultArea(
            @RequestParam("areaID") int areaId,
            @RequestParam("userId") int userID
    ) {
        return areaService.changeDefaultArea(areaId, userID);
    }

    @PostMapping
    public ResponseEntity<BaseResponseEntity<?>> addAreaToUser(@Valid @RequestBody AddAreaRequestDto addAreaRequestDto) {
        areaService.addAreaToUser(
                addAreaRequestDto.getAreaId(),
                addAreaRequestDto.getUserID(),
                addAreaRequestDto.getAreaRange(),
                addAreaRequestDto.isDefault()
        );
        return BaseResponseEntity.ok("success");
    }
}
