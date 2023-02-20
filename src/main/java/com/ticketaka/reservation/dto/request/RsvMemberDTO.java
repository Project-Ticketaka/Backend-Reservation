package com.ticketaka.reservation.dto.request;

import com.ticketaka.reservation.domain.RsvMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RsvMemberDTO {
    private Long memberId;
    private String memberEmail;

    public RsvMember reqToEntity(){
        return RsvMember.builder()
                .memberId(memberId)
                .memberEmail(memberEmail)
                .build();
    }
}
