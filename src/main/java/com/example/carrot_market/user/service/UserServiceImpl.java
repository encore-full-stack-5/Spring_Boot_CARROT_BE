package com.example.carrot_market.user.service;

import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.area.domain.model.UserArea;
import com.example.carrot_market.area.service.AreaService;
import com.example.carrot_market.core.CommonError;
import com.example.carrot_market.user.db.UserMapper;
import com.example.carrot_market.user.db.UserRepository;
import com.example.carrot_market.user.domain.User;
import com.example.carrot_market.user.domain.UserAggregate;
import com.example.carrot_market.user.dto.UpdateUserRequestDto;
import com.example.carrot_market.user.dto.request.SignInResponseDto;
import com.example.carrot_market.user.dto.request.SignUpRequestDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final AreaService areaService;

    public UserServiceImpl(UserMapper userMapper, AreaService areaService) {
        this.userMapper = userMapper;
        this.areaService = areaService;
    }

    @Override
    @Transactional
    public UserAggregate singUp(SignUpRequestDto singUpRequestDto) {

        userMapper.selectUserByPhone(singUpRequestDto.getPhone()).ifPresent(user1 -> {
                    throw new CommonError.Expected.ResourceNotFoundException("이미 회원 가입이 되어있는 번호입니다.");
                }
        );

        User user = makeUser(singUpRequestDto);
        userMapper.insertUser(user);

        areaService.addAreaToUser(
                singUpRequestDto.getAreaId(),
                user.getId(),
                singUpRequestDto.getAreaRange()
        );

        Area area = areaService.selectAreaById(singUpRequestDto.getAreaId());

        UserArea userArea = getUserArea(singUpRequestDto, area);

        return UserAggregate.builder()
                .user(user)
                .userArea(userArea)
                .build();
    }

    private UserArea getUserArea(SignUpRequestDto singUpRequestDto, Area area) {
        return UserArea.builder()
                .areas(List.of(area))
                .defaultAreaId(area.getId())
                .currentRange(AreaRange.convertIDToAreaRange(singUpRequestDto.getAreaRange()))
                .build();
    }

    private static User makeUser(SignUpRequestDto singUpRequestDto) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        return User.builder()
                .id(0)
                .userScore(36.5)
                .phone(singUpRequestDto.getPhone())
                .createdAt(timestamp)
                .nickname(singUpRequestDto.getNickname())
                .profileImage(singUpRequestDto.getProfileImage())
                .build();
    }

    @Override
    public UserAggregate singIn(SignInResponseDto signInResponseDto) {
        return null;
    }

    @Override
    public User updateUser(UpdateUserRequestDto updateUserRequestDto) {
        return null;
    }

    @Override
    public User getUser(String phone) {
        return userMapper.selectUserByPhone(phone).orElseThrow(() -> new CommonError.Expected.ResourceNotFoundException("찾는 유저가 없습니다."));
    }

    @Override
    public Boolean unRegister(String phoneNumber) {
        return null;
    }
}
