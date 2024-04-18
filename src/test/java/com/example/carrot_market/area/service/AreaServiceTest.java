package com.example.carrot_market.area.service;

import com.example.carrot_market.area.domain.model.Area;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class AreaServiceTest {

    @Autowired
    AreaService areaService;

    @BeforeEach
    void setUp() {


    }

    @Test
    @Transactional
    void selectAreaById() {
        Area area = areaService.selectAreaById(1);
        Area area2 = new Area(
            1,
            "서울특별시",
            "강동구",
            "천호동",
            "NULL",
            "NULL",
            37.5450159,
            127.1368066
        );

        Assertions.assertEquals(area, area2);
    }

    @Test
    void validateAreaToUserDefault() {
    }

    @Test
    void getAreaToLatLon() {
    }

    @Test
    void changeDefaultArea() {
    }

    @Test
    void addAreaToUser() {

    }

    @Test
    void getAreaListByUserId() {
    }
}