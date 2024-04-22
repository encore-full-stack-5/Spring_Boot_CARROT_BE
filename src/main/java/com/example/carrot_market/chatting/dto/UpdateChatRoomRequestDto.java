package com.example.carrot_market.chatting.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class UpdateChatRoomRequestDto {
    private int id;
    private int sellerLastReadChatId;
    private int customerLastReadChatId;
    private Integer isExistSeller;
    private Integer isExistCustomer;
    private String lastChat;

    @JsonCreator
    public UpdateChatRoomRequestDto(
            @JsonProperty("id") int id,
            @JsonProperty("sellerLastReadChatId") int sellerLastReadChatId,
            @JsonProperty("customerLastReadChatId") int customerLastReadChatId,
            @JsonProperty("isExistSeller") Integer isExistSeller,
            @JsonProperty("isExistCustomer") Integer isExistCustomer,
            @JsonProperty("lastChat") String lastChat) {
        this.id = id;
        this.sellerLastReadChatId = sellerLastReadChatId;
        this.customerLastReadChatId = customerLastReadChatId;
        this.isExistSeller = isExistSeller;
        this.isExistCustomer = isExistCustomer;
        this.lastChat = lastChat;
    }
}