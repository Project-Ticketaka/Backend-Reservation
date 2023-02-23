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
@Table(name="RESERVATION", indexes = @Index(name = "idx__member_id", columnList = "member_id"))
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY //AUTO
    @Column(name = "reservation_id", updatable = false)
    private Long reservationId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "performance_id", nullable = false)
    private String performanceId;

    @Column(name = "performance_title", nullable = false)
    private String performanceTitle;

    @Column(name = "reservation_ticket_count", nullable = false)
    private int reservationTicketCount;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "reservation_time", nullable = false)
    private String reservationTime;

    @Column(name = "reservation_price", nullable = false)
    private int reservationPrice;

    @Column(name = "reservation_poster", columnDefinition = "TEXT", nullable = false)
    private String reservationPoster;

    @Column(name = "reservation_deleted ", nullable = false)
    private String reservationDeleted;

    @Column(name = "reservation_createAt", nullable = false)
    private LocalDateTime reservationCreateAt;

    @PrePersist
    public void prePersist(){
        this.reservationDeleted = this.reservationDeleted == null ? "N" : this.reservationDeleted;
    }


    public ReservationListDTO toReservationResponse(){
        return ReservationListDTO.builder()
                .reservationId(reservationId)
                .reservationTicketCount(reservationTicketCount)
                .reservationDate(reservationDate)
                .reservationTime(reservationTime)
                .reservationPrice(reservationPrice)
                .reservationPoster(reservationPoster)
                .reservationTotalPrice(reservationPrice*reservationTicketCount)
                .reservationDeleted(reservationDeleted)
                .reservationCreateAt(reservationCreateAt)
                .build();
    }
}
