package com.ticketaka.reservation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.ticketaka.reservation.domain.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long memberId;
    private String performanceId;
    private String performanceTitle;
    private int reservationTicketCount;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate reservationDate;
    private String reservationTime;
    private int reservationPrice;
    private String reservationPoster;
    private String reservationCreateAt;

    // rabbitmq message setter
    private Long reservationId;
    private String memberEmail;

    public Reservation reqToEntity(){
        LocalDateTime now = LocalDateTime.now();
        return Reservation.builder()
                .memberId(memberId)
                .performanceId(performanceId)
                .performanceTitle(performanceTitle)
                .reservationTicketCount(reservationTicketCount)
                .reservationDate(reservationDate)
                .reservationTime(reservationTime)
                .reservationPrice(reservationPrice)
                .reservationPoster(reservationPoster)
                .reservationCreateAt(now)
                .build();
    }
}
