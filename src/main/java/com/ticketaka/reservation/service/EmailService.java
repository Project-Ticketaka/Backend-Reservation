package com.ticketaka.reservation.service;

import com.ticketaka.reservation.dto.request.ReservationDTO;
import com.ticketaka.reservation.dto.response.ReservationListDTO;

public interface EmailService {
    void sendMail(ReservationDTO reservationDTO);
}
