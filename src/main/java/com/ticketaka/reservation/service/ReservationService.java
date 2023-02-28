package com.ticketaka.reservation.service;

import com.ticketaka.reservation.dto.request.ReservationDTO;
import com.ticketaka.reservation.dto.response.ReservationListDTO;

import java.util.List;

public interface ReservationService {

    void reservation(ReservationDTO dto);

    List<ReservationListDTO> getReservationList(Long memberId);

    ReservationListDTO getReservation(Long reservationId);

    void deleteReservation(Long reservationId);

}
