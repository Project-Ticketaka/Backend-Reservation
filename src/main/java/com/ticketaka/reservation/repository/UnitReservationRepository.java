package com.ticketaka.reservation.repository;

import com.ticketaka.reservation.domain.Reservation;
import com.ticketaka.reservation.domain.UnitReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitReservationRepository extends JpaRepository<UnitReservation, Long> {

    List<UnitReservation> findByMemberId(Long memberId);
}
