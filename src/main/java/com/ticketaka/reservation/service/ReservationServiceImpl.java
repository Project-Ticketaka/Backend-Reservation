package com.ticketaka.reservation.service;

import com.ticketaka.reservation.domain.Reservation;
import com.ticketaka.reservation.dto.request.ReservationDTO;
import com.ticketaka.reservation.dto.response.ReservationListDTO;
import com.ticketaka.reservation.repository.MemberRepository;
import com.ticketaka.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void reservation(ReservationDTO dto) {
        try{
            reservationRepository.save(dto.reqToEntity());
            String memberEmail = memberRepository.findByEmail(dto.getMemberId());
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationListDTO> getReservationList(Long memberId) {
        List<Reservation> reservationList = reservationRepository.findByMemberId(memberId);
        return reservationList.stream().map(Reservation::toReservationResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationListDTO getReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).get();
        return reservation.toReservationResponse();
    }

    @Override
    public void deleteReservation(Long reservationId) {
        try{
            reservationRepository.deleteById(reservationId);
        }catch (Exception e) {
            throw e;
        }
    }
}
