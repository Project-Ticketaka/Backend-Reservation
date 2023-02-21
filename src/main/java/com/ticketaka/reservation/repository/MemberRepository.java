package com.ticketaka.reservation.repository;


import com.ticketaka.reservation.domain.Reservation;
import com.ticketaka.reservation.domain.RsvMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<RsvMember, Long> {
    @Query(value = "select member_email from rsv_member where member_id = :memberId", nativeQuery = true)
    String findByEmail(@Param("memberId")Long memberId);
}
