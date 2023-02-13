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
    private Long member_id;
    private String performance_id;
    private int reservation_ticket_count;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate reservation_date;
    private String reservation_time;
    private int reservation_price;
    private String reservation_poster;
    private String reservation_createAt;

    public Reservation reqToEntity(){
        LocalDateTime now = LocalDateTime.now();
        return Reservation.builder()
                .memberId(member_id)
                .performanceId(performance_id)
                .reservationTicketCount(reservation_ticket_count)
                .reservationDate(reservation_date)
                .reservationTime(reservation_time)
                .reservationPrice(reservation_price)
                .reservationPoster(reservation_poster)
                .reservationCreateAt(now)
                .build();
    }
}
