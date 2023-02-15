package com.ticketaka.reservation.controller;

import com.ticketaka.reservation.dto.mail.EmailMessageDTO;
import com.ticketaka.reservation.dto.request.ReservationDTO;
import com.ticketaka.reservation.dto.response.ReservationListDTO;
import com.ticketaka.reservation.service.EmailService;
import com.ticketaka.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final EmailService emailService;

    @PostMapping("/create")
    public ResponseEntity<String> reservation(@RequestBody ReservationDTO dto) {
//        emailService.sendMail(dto);
        return reservationService.reservation(dto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ReservationListDTO>> reservationList(@RequestBody Map<String, Long> memberMap) {
        List<ReservationListDTO> reservationList = reservationService.getReservationList(memberMap.get("member_id"));
        return ResponseEntity.ok(reservationList);
    }

    @GetMapping("/list/{rsv_id}")
    public ResponseEntity<ReservationListDTO> reservationInfo(
            @PathVariable("rsv_id") Long reservationId) {
        ReservationListDTO reservationList = reservationService.getReservation(reservationId);
        return ResponseEntity.ok(reservationList);
    }

    @DeleteMapping("/delete/{rsv_id}")
    public ResponseEntity<String> deleteReservation(
            @PathVariable("rsv_id") Long reservationId
    ) {
        return reservationService.deleteReservation(reservationId);
    }
}
