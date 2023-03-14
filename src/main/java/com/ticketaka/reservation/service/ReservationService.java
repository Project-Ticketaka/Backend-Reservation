package com.ticketaka.reservation.service;

import com.ticketaka.reservation.dto.request.ReservationDTO;
import com.ticketaka.reservation.dto.response.ReservationListDTO;

import java.util.List;
import java.util.Map;

public interface ReservationService {

    void reservation(ReservationDTO dto, Long memberId);

    List<ReservationListDTO> getReservationList(Long memberId);

    ReservationListDTO getReservation(Long reservationId);

    void deleteReservation(Long reservationId);

}
