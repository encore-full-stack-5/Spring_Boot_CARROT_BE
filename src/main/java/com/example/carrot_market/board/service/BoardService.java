package com.example.carrot_market.board.service;

import com.example.carrot_market.board.domain.model.Board;
import com.example.carrot_market.board.domain.model.Comment;
import com.example.carrot_market.board.dto.*;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    /**
     * @param addBoardRequestDto, userId
     * @return Board
     *
     * 사용자가 커뮤니티 글을 작성한다.
     */
    void insertBoard(
            AddBoardRequestDto addBoardRequestDto
//            int userId
    );

    /**
     * @param userId
     * @return List<Board>
     *
     * 사용자가 작성한 커뮤니티 글목록을 조회한다.
     */
    List<Board> getBoardListByUserId(int userId);

    /**
     * @param areaId
     * @return List<Board>
     *
     * 설정한 지역의 커뮤니티 글목록을 전체 조회한다.
     */
    List<Board> getBoardListByAreaId(int areaId);

    /**
     * @param boardId
     * @return Board
     *
     * 선택한 커뮤니티의 정보를 조회한다.
     */
    getDetailBoardResultDto getDetailBoard(int boardId);

    /**
     * @param request, boardId
     * @return UpdateBoardRequestDto
     *
     * 사용자가 작성한 커뮤니티의 정보를 수정한다.
     */
    UpdateBoardRequestDto updateBoard(UpdateBoardRequestDto request, int id);

    /**
     * @param boardId
     * @return boolean
     *
     * 커뮤니티의 조회수를 증가한다.
     */
    boolean increaseBoardViewCount(int boardId);

    /**
     * @param boardId
     * @return void
     *
     * 커뮤니티 글에 좋아요 선택/취소
     */
    void likeBoard(int boardId);
    void unLikeBoard(int boardId);

    /**
     * @param id
     * @return Board
     *
     * 커뮤니티 글을 삭제한다.
     */
    void deleteBoard(int id);

    /**
     * @param addCommentRequestDto
     * @return Comment
     *
     * 커뮤니티 글에 댓글을 작성한다.
     */
    void insertComment(
            AddCommentRequestDto addCommentRequestDto
    );

    // 단일 댓글 조회
    Optional<Comment> selectCommentById(int id);

    // 커뮤니티 내 댓글 조회
    List<Comment> getCommentsByBoardId(int boardId);

    /**
     * @param boardId, commentId, userId
     * @return void
     *
     * 커뮤니티 글 > 댓글 좋아요 선택/취소
     */
    void likeComment(int boardId, int commentId, int userId);
    void unLikeComment(int boardId, int commentId, int userId);

    /**
     * @param id
     * @return Comment
     *
     * 커뮤니티 글 > 댓글을 삭제한다.
     */
    void deleteComment(int id);

    /**
     * @param addNestedCommentRequestDto, boardId, commentId, userId
     * @return Comment
     *
     * 커뮤니티 글 > 댓글 > 답글을 작성한다.
     */
    Comment insertNestedComment(
            AddNestedCommentRequestDto addNestedCommentRequestDto,
            int boardId,
            int commentId,
            int userId
    );

    /**
     * @param boardId, commentId, nestedCommentId, userId
     * @return void
     *
     * 커뮤니티 글 > 댓글 > 답글 좋아요 선택/취소
     */
    void likeNestedComment(int boardId, int commentId, int nestedCommentId, int userId);
    void unLikeNestedComment(int boardId, int commentId, int nestedCommentId, int userId);

    /**
     * @param boardId, commentId, nestedCommentId, userId
     * @return Comment
     *
     * 커뮤니티 글 > 댓글 > 답글을 삭제한다.
     */
    Comment deleteNestedComment(int boardId, int commentId, int nestedCommentId, int userId);
}
