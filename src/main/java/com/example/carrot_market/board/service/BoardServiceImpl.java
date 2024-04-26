package com.example.carrot_market.board.service;

import com.example.carrot_market.board.db.BoardMapper;
import com.example.carrot_market.board.domain.model.Board;
import com.example.carrot_market.board.domain.model.Comment;
import com.example.carrot_market.board.dto.*;
import com.example.carrot_market.core.error.CommonError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    @Autowired
    private final BoardMapper boardMapper;

    // 커뮤니티 글 작성
    @Override
    public void insertBoard(AddBoardRequestDto request) {
        boardMapper.insertBoard(request);
    }

    // 사용자가 작성한 커뮤니티 글목록 조회
    @Override
    public List<Board> getBoardListByUserId(int userId) {
        return boardMapper.getBoardListByUserId(userId);
    }

    // 설정한 지역의 커뮤니티 글목록 전체 조회
    @Override
    public List<Board> getBoardListByAreaId(int areaId) {
        return boardMapper.getBoardListByAreaId(areaId);
    }

    // 선택한 커뮤니티의 정보 조회
    @Override
    public getDetailBoardResultDto getDetailBoard(int boardId) {
        return boardMapper.getDetailBoard(boardId);
    }

    // 사용자가 작성한 커뮤니티의 정보 수정
    @Override
    public UpdateBoardRequestDto updateBoard(UpdateBoardRequestDto request, int id) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp updateAt = Timestamp.valueOf(now);
        Optional<Board> boardById = boardMapper.selectBoardById(id);
        if(boardById.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist board");

        UpdateBoardRequestDto updateData = UpdateBoardRequestDto.builder()
                .id(request.getId())
                .content(request.getContent())
                .title(request.getTitle())
                .category(request.getCategory())
                .updateAt(updateAt)
                .build();
        System.out.println(updateData.toString());
        boardMapper.updateBoard(updateData);
        return updateData;
    }

    @Override
    public boolean increaseBoardViewCount(int boardId) {
        return false;
    }

    @Override
    public void likeBoard(int boardId) {

    }

    @Override
    public void unLikeBoard(int boardId) {

    }

    // 선택한 커뮤니티 삭제
    @Override
    public void deleteBoard(int id) {
        boardMapper.deleteBoard(id);
    }

    // 댓글 작성
    @Override
    public void insertComment(AddCommentRequestDto addCommentRequestDto) {
        boardMapper.insertComment(addCommentRequestDto);
    }

    // 단일 댓글 조회
    public Comment selectCommentById(int id) {
        return boardMapper.selectCommentById(id);
    }

    @Override
    public void likeComment(int boardId, int commentId, int userId) {

    }

    @Override
    public void unLikeComment(int boardId, int commentId, int userId) {

    }

    @Override
    public Comment deleteComment(int boardId, int commentId, int userId) {
        return null;
    }

    @Override
    public Comment insertNestedComment(AddNestedCommentRequestDto addNestedCommentRequestDto, int boardId, int commentId, int userId) {
        return null;
    }

    @Override
    public void likeNestedComment(int boardId, int commentId, int nestedCommentId, int userId) {

    }

    @Override
    public void unLikeNestedComment(int boardId, int commentId, int nestedCommentId, int userId) {

    }

    @Override
    public Comment deleteNestedComment(int boardId, int commentId, int nestedCommentId, int userId) {
        return null;
    }
}
