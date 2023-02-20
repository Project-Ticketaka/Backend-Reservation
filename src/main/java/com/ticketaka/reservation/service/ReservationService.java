package com.ticketaka.reservation.service;

import com.ticketaka.reservation.dto.request.ReservationDTO;
import com.ticketaka.reservation.dto.response.ReservationListDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {

    ResponseEntity<String> reservation(ReservationDTO dto);

    List<ReservationListDTO> getReservationList(Long memberId);

    ReservationListDTO getReservation(Long reservationId);

    ResponseEntity<String> deleteReservation(Long reservationId);

}
