package com.ticketaka.reservation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
    OK(200, "성공"),

    DB_UNABLE_TO_CONNECT(500, "데이터 베이스에 연결할 수 없습니다."),

    RABBITMQ_UNABLE_TO_CONNECT(500, "Rabbitmq와 통신 할 수 없습니다."),

    NO_RESERVATION_LIST(202, "예약 정보가 없습니다.");

    private final int code;
    private final String description;
}
