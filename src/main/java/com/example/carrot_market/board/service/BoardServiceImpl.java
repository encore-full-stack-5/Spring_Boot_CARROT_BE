package com.example.carrot_market.board.service;

import com.example.carrot_market.board.db.BoardMapper;
import com.example.carrot_market.board.domain.model.Board;
import com.example.carrot_market.board.domain.model.Comment;
import com.example.carrot_market.board.dto.AddBoardRequestDto;
import com.example.carrot_market.board.dto.AddCommentRequestDto;
import com.example.carrot_market.board.dto.AddNestedCommentRequestDto;
import com.example.carrot_market.board.dto.UpdateBoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<Board> getBoardListByAreaId(int areaId) {
        return null;
    }

    @Override
    public Board getDetailBoard(int boardId) {
        return null;
    }

    @Override
    public UpdateBoardRequestDto updateBoardByUserId(int userId, int boardId) {
        return null;
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

    @Override
    public Board deleteBoard(int boardId) {
        return null;
    }

    @Override
    public Comment insertComment(AddCommentRequestDto addCommentRequestDto, int boardId, int userId) {
        return null;
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
