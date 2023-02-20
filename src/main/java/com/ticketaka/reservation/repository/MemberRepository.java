package com.ticketaka.reservation.repository;


import com.ticketaka.reservation.domain.Reservation;
import com.ticketaka.reservation.domain.RsvMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<RsvMember, Long> {
}
