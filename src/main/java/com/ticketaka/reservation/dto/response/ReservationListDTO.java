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
    private Long reservationId;
    private int reservationTicketCount;
    private String performanceTitle;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate reservationDate;
    private String reservationTime;
    private int reservationPrice;
    private int reservationTotalPrice;
    private String reservationPoster;
    private String reservationDeleted;
    private LocalDateTime reservationCreateAt;

}
