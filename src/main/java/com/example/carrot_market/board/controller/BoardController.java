package com.example.carrot_market.board.controller;

import com.example.carrot_market.board.domain.model.Board;
import com.example.carrot_market.board.dto.AddBoardRequestDto;
import com.example.carrot_market.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{id}")
    public List<Board> getBoardListByUserId(
            @PathVariable("id") int userId
    ) {
        return boardService.getBoardListByUserId(userId);
    }
}
