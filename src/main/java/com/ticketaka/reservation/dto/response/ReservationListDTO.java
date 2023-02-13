package com.ticketaka.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationListDTO {
    private Long reservation_id;
    private int reservation_ticket_count;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate reservation_date;
    private String reservation_time;
    private int reservation_price;
    private int reservation_total_price;
    private String reservation_poster;
    private String reservation_deleted;
    private LocalDateTime reservation_createAt;

}
