package com.ticketaka.reservation.dto.mail;

import com.ticketaka.reservation.dto.response.ReservationListDTO;
import lombok.*;

@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class EmailMessageDTO {
    private String to;
    private String subject;
    private String message;
}
