package com.example.carrot_market.board.db;

import com.example.carrot_market.board.domain.model.Board;
import com.example.carrot_market.board.dto.AddBoardRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    void insertBoard(AddBoardRequestDto request);
}
