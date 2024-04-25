package com.example.carrot_market.board.controller;

import com.example.carrot_market.board.dto.AddBoardRequestDto;
import com.example.carrot_market.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
