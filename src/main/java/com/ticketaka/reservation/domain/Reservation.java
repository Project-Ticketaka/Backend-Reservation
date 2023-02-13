package com.ticketaka.reservation.domain;

import com.ticketaka.reservation.dto.response.ReservationListDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY //AUTO
    @Column(name = "reservation_id", updatable = false)
    private Long reservationId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "performance_id")
    private String performanceId;

    @Column(name = "reservation_ticket_count")
    private int reservationTicketCount;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "reservation_time")
    private String reservationTime;

    @Column(name = "reservation_price")
    private int reservationPrice;

    @Column(name = "reservation_poster", columnDefinition = "TEXT")
    private String reservationPoster;

    @Column(name = "reservation_deleted ")
    private String reservationDeleted;

    @Column(name = "reservation_createAt")
    private LocalDateTime reservationCreateAt;

    @PrePersist
    public void prePersist(){
        this.reservationDeleted = this.reservationDeleted == null ? "Y" : this.reservationDeleted;
    }


    public ReservationListDTO toReservationResponse(){
        return ReservationListDTO.builder()
                .reservation_id(reservationId)
                .reservation_ticket_count(reservationTicketCount)
                .reservation_date(reservationDate)
                .reservation_time(reservationTime)
                .reservation_price(reservationPrice)
                .reservation_poster(reservationPoster)
                .reservation_total_price(reservationPrice*reservationTicketCount)
                .reservation_deleted(reservationDeleted)
                .reservation_createAt(reservationCreateAt)
                .build();
    }
}
