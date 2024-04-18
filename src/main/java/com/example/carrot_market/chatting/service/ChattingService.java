package com.example.carrot_market.chatting.service;


// 웹소켓
public interface ChattingService {

    /**
     *
     * @param productId
     * @param userId
     *
     */
    void createChattingRoom(int productId, int userId);
    void joinChattingRoom();

    /**
     * @param ChattingRoomId
     * @param userId
     * @return boolean
     *
     * 채팅방에 사용자를 추가한다.
     * @param ChattingRoomId
     * @param userId
     */
    void addUserToChattingRoom(int ChattingRoomId, int userId);
    void sendMessage();
    void deleteChattingRoom();


}
