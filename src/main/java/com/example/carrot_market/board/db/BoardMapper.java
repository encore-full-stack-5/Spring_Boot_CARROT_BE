package com.example.carrot_market.board.db;

import com.example.carrot_market.board.domain.model.Board;
import com.example.carrot_market.board.domain.model.Comment;
import com.example.carrot_market.board.dto.AddBoardRequestDto;
import com.example.carrot_market.board.dto.AddCommentRequestDto;
import com.example.carrot_market.board.dto.UpdateBoardRequestDto;
import com.example.carrot_market.board.dto.getDetailBoardResultDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

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

    // 단일 커뮤니티 조회
    Optional<Board> selectBoardById(int id);

    // 사용자가 작성한 커뮤니티의 정보 수정
    void updateBoard(UpdateBoardRequestDto request);

    // 커뮤니티 조회수
    boolean increaseBoardViewCount(int boardId);

    // 선택한 커뮤니티 삭제
    void deleteBoard(int id);
    // 선택한 커뮤니티의 댓글도 함께 삭제
    void deleteBoardWithComment(int id);
  
    // 댓글 작성
    void insertComment(AddCommentRequestDto addCommentRequestDto);

    // 단일 댓글 조회
    Optional<Comment> selectCommentById(int id);

    // 커뮤니티 내 댓글 조회
    List<Comment> getCommentsByBoardId(int boardId);

    // 댓글 삭제
    void deleteComment(int id);
}
