package com.ticketaka.reservation.repository;



import com.ticketaka.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    @Override
    Optional<Reservation> findById(Long reservationId);

    List<Reservation> findByMemberId(Long memberId);
}
