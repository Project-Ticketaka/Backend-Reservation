package com.ticketaka.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ticketaka.reservation.dto.StatusCode;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"code", "message", "data"})
public class BaseResponse<T> {
    private int code;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.description = statusCode.getDescription();
        this.data = data;
    }

    public BaseResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();;
        this.description = statusCode.getDescription();
    }
}
