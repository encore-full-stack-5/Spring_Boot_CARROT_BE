package com.example.carrot_market.user.service;

import com.example.carrot_market.area.domain.model.Area;
import com.example.carrot_market.area.domain.model.AreaRange;
import com.example.carrot_market.area.domain.model.UserArea;
import com.example.carrot_market.area.service.AreaService;
import com.example.carrot_market.core.error.CommonError;
import com.example.carrot_market.user.db.UserMapper;
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
import java.util.Optional;

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
    public UserAggregate signUp(SignUpRequestDto singUpRequestDto) {

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

        UserArea userArea = getUserArea(user, area, singUpRequestDto);

        return UserAggregate.builder()
                .user(user)
                .userArea(List.of(userArea))
                .build();
    }

    private UserArea getUserArea(User user, Area area, SignUpRequestDto singUpRequestDto) {
        return UserArea.builder()
                .userId(user.getId())
                .areaId(singUpRequestDto.getAreaId())
                .do_city(area.getDo_city())
                .si_gu(area.getSi_gu())
                .dong(area.getDong())
                .eup(area.getEup())
                .ri(area.getRi())
                .latitude(area.getLatitude())
                .longitude(area.getLongitude())
                .isDefault(1)
                .areaRange(singUpRequestDto.getAreaRange())
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

        log.error("signInResponseDto : {}", signInResponseDto.getPhone());
        User user = userMapper.selectUserByPhone(signInResponseDto.getPhone()).orElseThrow(() -> {
            throw new CommonError.Expected.ResourceNotFoundException("회원정보가 존재하지 않습니다.");
        });


        List<UserArea> areas = areaService.getAreaListByUserId(user.getId());
        return UserAggregate.builder()
                .user(user)
                .userArea(areas)
                .build();
    }
    @Override
    public User updateUser(int id, UpdateUserRequestDto updateUserRequestDto){
        Optional<User> user1 = userMapper.selectUserById(id);
        if(user1.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist user");
        User user = updateUserRequestDto.toEntity(id);
        userMapper.updateUser(user);
        return user;
    }
    @Override
    public User getUser(String phone) {
        return userMapper.selectUserByPhone(phone).orElseThrow(() -> new CommonError.Expected.ResourceNotFoundException("찾는 유저가 없습니다."));
    }

    @Override
    public User unRegister(int id) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp deletedAt = Timestamp.valueOf(now);
        Optional<User> userById = userMapper.selectUserById(id);
        if(userById.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist user");
        User user = User.builder()
                .id(userById.get().getId())
                .nickname(userById.get().getNickname())
                .phone(userById.get().getPhone())
                .createdAt(userById.get().getCreatedAt())
                .userScore(userById.get().getUserScore())
                .profileImage(userById.get().getProfileImage())
                .deletedAt(deletedAt)
                .build();
        userMapper.unRegister(user);
        return user;
    }

}
