package com.ticketaka.reservation.service;

import com.ticketaka.reservation.dto.request.RsvMemberDTO;
import com.ticketaka.reservation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ResponseEntity<String> createMember(RsvMemberDTO dto) {
        try{
            memberRepository.save(dto.reqToEntity());
            return ResponseEntity.ok("SUCCESS_RESERVATION");
        }catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.badRequest().body("FAIL_RESERVATION");
        }
    }
}
