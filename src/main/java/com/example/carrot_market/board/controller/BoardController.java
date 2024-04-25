package com.example.carrot_market.board.controller;

import com.example.carrot_market.board.domain.model.Board;
import com.example.carrot_market.board.dto.AddBoardRequestDto;
import com.example.carrot_market.board.dto.UpdateBoardRequestDto;
import com.example.carrot_market.board.dto.getDetailBoardResultDto;
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
    @PutMapping("/update/{id}")
    public UpdateBoardRequestDto updateBoard(
            @RequestBody UpdateBoardRequestDto request,
            @PathVariable("id") int id
    ) {
        return boardService.updateBoard(request, id);
    }
}
