package com.example.carrot_market.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseEntity<T> {
    // Getters
    @JsonProperty("code")
    private final int code;
    @JsonProperty("status")
    private final HttpStatus status;
    @JsonProperty("message")
    private final String message;
    @JsonProperty("data")
    private final T data;

    public BaseResponseEntity(int code, HttpStatus status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public BaseResponseEntity(int code, HttpStatus status, String message) {
        this(code, status, message, null);
    }

    public static <T> ResponseEntity<BaseResponseEntity<T>> ok(T data, String message) {
        HttpStatus ok = HttpStatus.OK;
        return ResponseEntity.status(ok)
                             .body(new BaseResponseEntity<>(ok.value(), ok, message != null ? message : "success", data));
    }

    public static ResponseEntity<BaseResponseEntity<?>> ok(String message) {
        HttpStatus ok = HttpStatus.OK;
        return ResponseEntity.status(ok)
                             .body(new BaseResponseEntity<>(ok.value(), ok, message != null ? message : "success"));
    }

    public static <T> ResponseEntity<BaseResponseEntity<T>> create(T data, String message) {
        HttpStatus created = HttpStatus.CREATED;
        return ResponseEntity.status(created)
                             .body(new BaseResponseEntity<>(created.value(), created, message != null ? message : "success", data));
    }

    public static ResponseEntity<BaseResponseEntity<?>> create(String message) {
        HttpStatus created = HttpStatus.CREATED;
        return ResponseEntity.status(created)
                             .body(new BaseResponseEntity<>(created.value(), created, message != null ? message : "success"));
    }

    public static ResponseEntity<BaseResponseEntity<?>> fail(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus)
                             .body(new BaseResponseEntity<>(httpStatus.value(), httpStatus, message != null ? message : httpStatus.getReasonPhrase()));
    }

}