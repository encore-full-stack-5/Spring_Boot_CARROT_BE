package com.example.carrot_market.board.db;

import com.example.carrot_market.board.domain.model.Board;
import com.example.carrot_market.board.dto.AddBoardRequestDto;
import com.example.carrot_market.board.dto.getDetailBoardResultDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 커뮤니티 글 작성
    void insertBoard(AddBoardRequestDto request);

    // 사용자가 작성한 커뮤니티 글목록 조회
    List<Board> getBoardListByUserId(int userId);

    // 설정한 지역의 커뮤니티 글목록 전체 조회
    List<Board> getBoardListByAreaId(int areaId);

    // 선택한 커뮤니티의 정보 조회
    getDetailBoardResultDto getDetailBoard(int boardId);
}
