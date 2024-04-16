package com.example.carrot_market.area.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserArea {

    List<Area> areas;
    int defaultAreaId;
    AreaRange currentRange;

}
