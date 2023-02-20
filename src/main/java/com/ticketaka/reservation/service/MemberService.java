package com.ticketaka.reservation.service;

import com.ticketaka.reservation.dto.request.RsvMemberDTO;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<String> createMember(RsvMemberDTO dto);
}
