package com.example.carrot_market.user.domain;

import com.example.carrot_market.area.domain.model.UserArea;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserAggregate {
    User user;
    List<UserArea> userArea;
}
