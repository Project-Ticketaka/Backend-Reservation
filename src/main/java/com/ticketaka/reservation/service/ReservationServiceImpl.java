package com.ticketaka.reservation.service;

import com.ticketaka.reservation.domain.Reservation;
import com.ticketaka.reservation.domain.UnitReservation;
import com.ticketaka.reservation.dto.request.ReservationDTO;
import com.ticketaka.reservation.dto.response.ReservationListDTO;
import com.ticketaka.reservation.repository.ReservationRepository;
import com.ticketaka.reservation.repository.UnitReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final UnitReservationRepository unitReservationRepository;
//    private final RabbitTemplate rabbitTemplate;

    @Override
    @Transactional
    public void reservation(ReservationDTO dto, Long memberId) {
        dto.setMemberId(memberId);
        Long reservationId = reservationRepository.save(dto.reqToEntity()).getReservationId();
        dto.setReservationId(reservationId);
//        rabbitTemplate.convertAndSend("mail.exchange", "mail.key", dto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationListDTO> getReservationList(Long memberId) {
        List<UnitReservation> reservationList = unitReservationRepository.findByMemberId(memberId);
        return reservationList.stream().map(UnitReservation::toReservationResponse).collect(Collectors.toList());
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
