package com.ticketaka.advice;

import com.ticketaka.exception.CustomException.NoDataReservationListException;
import com.ticketaka.reservation.dto.StatusCode;
import com.ticketaka.reservation.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.amqp.AmqpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoDataReservationListException.class)
    public ResponseEntity<BaseResponse> handleNoDataReservationList(Exception e) {
        e.printStackTrace();
        return ResponseEntity.accepted().body(new BaseResponse(StatusCode.NO_RESERVATION_LIST));
    }

    @ExceptionHandler(JDBCConnectionException.class)
    public ResponseEntity<BaseResponse> handleJDBCConnection(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new BaseResponse(StatusCode.DB_UNABLE_TO_CONNECT));
    }

    @ExceptionHandler(AmqpException.class)
    public ResponseEntity<BaseResponse> handleAmqp(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new BaseResponse(StatusCode.DB_UNABLE_TO_CONNECT));
    }
}
