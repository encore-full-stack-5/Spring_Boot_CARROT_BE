package com.example.carrot_market.board.controller;

import com.example.carrot_market.board.domain.model.Board;
import com.example.carrot_market.board.domain.model.Comment;
import com.example.carrot_market.board.dto.AddBoardRequestDto;
import com.example.carrot_market.board.dto.AddCommentRequestDto;
import com.example.carrot_market.board.dto.UpdateBoardRequestDto;
import com.example.carrot_market.board.dto.getDetailBoardResultDto;
import com.example.carrot_market.board.service.BoardService;
import com.example.carrot_market.core.base.BaseResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/boards")
@RequiredArgsConstructor
public class BoardController {
    @Autowired
    private final BoardService boardService;

    // 커뮤니티 글 작성
    @PostMapping
    public void insertBoard(
            @RequestBody AddBoardRequestDto addBoardRequestDto
    ) {
        boardService.insertBoard(addBoardRequestDto);
    }

    // 사용자가 작성한 커뮤니티 글목록 조회
    @GetMapping("/user/{id}")
    public List<Board> getBoardListByUserId(
            @PathVariable("id") int userId
    ) {
        return boardService.getBoardListByUserId(userId);
    }

    // 설정한 지역의 커뮤니티 글목록 전체 조회
    @GetMapping("/areas/{id}")
    public List<Board> getBoardListByAreaId(
            @PathVariable("id") int areaId
    ) {
        return boardService.getBoardListByAreaId(areaId);
    }

    // 선택한 커뮤니티의 정보 조회
    @GetMapping("/board/{id}")
    public getDetailBoardResultDto getDetailBoard(
            @PathVariable("id") int boardId
    ) {
        return boardService.getDetailBoard(boardId);
    }

    // 사용자가 작성한 커뮤니티의 정보 수정
    @PutMapping("/{id}")
    public UpdateBoardRequestDto updateBoard(
            @RequestBody UpdateBoardRequestDto request,
            @PathVariable("id") int id
    ) {
        return boardService.updateBoard(request, id);
    }

    // 커뮤니티 조회수
    @PutMapping("/view/{id}")
    public boolean increaseBoardViewCount(
            @PathVariable("id") int boardId
    ) {
        return boardService.increaseBoardViewCount(boardId);
    }

    // 선택한 커뮤니티 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseEntity<?>> deleteBoard(
            @PathVariable("id") int id // boardId
    ) {
        boardService.deleteBoard(id);

        return BaseResponseEntity.ok("success");
    }
  
    // 댓글 작성
    @PostMapping("/comment")
    public ResponseEntity<BaseResponseEntity<?>> insertComment(
            @RequestBody AddCommentRequestDto addCommentRequestDto
    ) {
        boardService.insertComment(addCommentRequestDto);
        return BaseResponseEntity.ok("success");
    }

    // 단일 댓글 조회
    @GetMapping("/comment/{id}")
    public Optional<Comment> selectCommentById(
            @PathVariable("id") int id // commentId
    ) {
        return boardService.selectCommentById(id);
    }

    // 커뮤니티 내 댓글 조회
    @GetMapping("/board/comments/{id}")
    public List<Comment> getCommentsByBoardId(
            @PathVariable("id") int boardId
    ) {
        return boardService.getCommentsByBoardId(boardId);
    }
  
    // 댓글 삭제
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<BaseResponseEntity<?>> deleteComment(
            @PathVariable("id") int id // commentId
    ) {
        boardService.deleteComment(id);

        return BaseResponseEntity.ok("success");
    }
}
