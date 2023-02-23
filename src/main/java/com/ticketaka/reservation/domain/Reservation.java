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

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "performance_id")
    private String performanceId;

    @Column(name = "performance_title")
    private String performanceTitle;

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
