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
        userMapper.selectUserByPhone(singUpRequestDto.getPhone()).ifPresent(user1 -> {throw new CommonError.Expected.ResourceNotFoundException("이미 회원 가입이 되어있는 번호입니다.");});
        User user = singUpRequestDto.toDomain();
        userMapper.insertUser(user);
        areaService.addAreaToUser(singUpRequestDto.getAreaId(), user.getId(), singUpRequestDto.getAreaRange());
        Area area = areaService.selectAreaById(singUpRequestDto.getAreaId());
        UserArea userArea = new UserArea(user, area, singUpRequestDto.getAreaRange());
        return UserAggregate.builder()
                .user(user)
                .userArea(List.of(userArea))
                .build();
    }


    @Override
    public UserAggregate singIn(SignInResponseDto signInResponseDto) {
        User user = userMapper.selectUserByPhone(signInResponseDto.getPhone()).orElseThrow(() -> new CommonError.Expected.ResourceNotFoundException("회원정보가 존재하지 않습니다."));
        List<UserArea> areas = areaService.getAreaListByUserId(user.getId());
        return UserAggregate.builder()
                .user(user)
                .userArea(areas)
                .build();
    }

    @Override
    public User updateUser(int id, UpdateUserRequestDto updateUserRequestDto){
        User user = updateUserRequestDto.toEntity(id);
        userMapper.updateUser(user);
        return userMapper.selectUserById(id).orElseThrow(() -> new CommonError.Expected.ResourceNotFoundException("찾는 유저가 없습니다."));
    }

    @Override
    public User getUser(String phone) {
        return userMapper.selectUserByPhone(phone).orElseThrow(() -> new CommonError.Expected.ResourceNotFoundException("찾는 유저가 없습니다."));
    }

    @Override
    public User unRegister(int id) {
        User user = userMapper.selectUserById(id).orElseThrow(() -> new CommonError.Expected.ResourceNotFoundException("찾는 유저가 없습니다."));
        if(user.getDeletedAt() != null) throw new CommonError.Unexpected.IllegalArgumentException("이미 탈퇴한 유저입니다.");
        user.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        userMapper.unRegister(user);
        return user;
    }

}
