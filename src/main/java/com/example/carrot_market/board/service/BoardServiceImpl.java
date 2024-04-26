package com.example.carrot_market.board.service;

import com.example.carrot_market.board.db.BoardMapper;
import com.example.carrot_market.board.domain.model.Board;
import com.example.carrot_market.board.domain.model.Comment;
import com.example.carrot_market.board.dto.*;
import com.example.carrot_market.core.error.CommonError;
import com.example.carrot_market.user.db.UserMapper;
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
    @Autowired
    private final UserMapper userMapper;

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

    // 커뮤니티 조회수
    @Override
    public boolean increaseBoardViewCount(int boardId) {
        Optional<Board> boardById = boardMapper.selectBoardById(boardId);
        if(boardById.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist board");

        return boardMapper.increaseBoardViewCount(boardId);
    }

    // 커뮤니티 좋아요
    @Override
    public void likeBoard(int boardId) {

    }

    @Override
    public void unLikeBoard(int boardId) {

    }

    // 선택한 커뮤니티 삭제
    @Override
    public void deleteBoard(int id) {
        Optional<Board> boardById = boardMapper.selectBoardById(id);
        if(boardById.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist board");

        boardMapper.deleteBoardWithComment(id);
        boardMapper.deleteBoard(id);
    }

    // 댓글 작성
    @Override
    public void insertComment(AddCommentRequestDto addCommentRequestDto) {
        boardMapper.insertComment(addCommentRequestDto);
    }

    // 단일 댓글 조회
    public Optional<Comment> selectCommentById(int id) {
        return boardMapper.selectCommentById(id);
    }

    // 커뮤니티 내 댓글 조회
    public List<Comment> getCommentsByBoardId(int boardId) {
        return boardMapper.getCommentsByBoardId(boardId);
    }

    @Override
    public void likeComment(int boardId, int commentId, int userId) {

    }

    @Override
    public void unLikeComment(int boardId, int commentId, int userId) {

    }

    @Override
    public void deleteComment(int id) {
        Optional<Comment> commentById = boardMapper.selectCommentById(id);
        if(commentById.isEmpty()) throw new CommonError.Expected.ResourceNotFoundException("no exist comment");

        boardMapper.deleteComment(id);
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
