package com.ticketaka.reservation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ticketaka.reservation.domain.Reservation;
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
public class ReservationDTO {
    private Long memberId;
    private String memberEmail;
    private String performanceId;
    private int reservationTicketCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate reservationDate;
    private String reservationTime;
    private int reservationPrice;
    private String reservationPoster;
    private String reservationCreateAt;

    public Reservation reqToEntity(){
        LocalDateTime now = LocalDateTime.now();
        return Reservation.builder()
                .memberId(memberId)
                .performanceId(performanceId)
                .reservationTicketCount(reservationTicketCount)
                .reservationDate(reservationDate)
                .reservationTime(reservationTime)
                .reservationPrice(reservationPrice)
                .reservationPoster(reservationPoster)
                .reservationCreateAt(now)
                .build();
    }
}
