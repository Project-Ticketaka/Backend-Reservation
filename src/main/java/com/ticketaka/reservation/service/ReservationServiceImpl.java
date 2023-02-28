package com.ticketaka.reservation.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.ticketaka.reservation.domain.Reservation;
import com.ticketaka.reservation.domain.UnitReservation;
import com.ticketaka.reservation.dto.request.ReservationDTO;
import com.ticketaka.reservation.dto.response.ReservationListDTO;
import com.ticketaka.reservation.repository.MemberRepository;
import com.ticketaka.reservation.repository.ReservationRepository;
import com.ticketaka.reservation.repository.UnitReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final UnitReservationRepository unitReservationRepository;
    private final MemberRepository memberRepository;

    private  final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Override
    @Transactional
    public void reservation(ReservationDTO dto) {
        try{
            // 예약 정보 INSERT
            Long reservationId = reservationRepository.save(dto.reqToEntity()).getReservationId();

            // mail 관련
//            String memberEmail = memberRepository.findByEmail(dto.getMemberId());
//
//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//
//
//            Context context = new Context();
//            context.setVariable("data", dto);
//            context.setVariable("reservationId", reservationId);
//
//            String message = templateEngine.process("mail/mail-ticket2", context);
//
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//            mimeMessageHelper.setTo(memberEmail); // 메일 수신자
//            mimeMessageHelper.setSubject("[Ticketaka] 예약 정보"); // 메일 제목
//            mimeMessageHelper.setText(message, true); // 메일 본문 내용, HTML 여부
//
//
//            javaMailSender.send(mimeMessage);
//
//            log.info("Success Send Email");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
        }
        catch (Exception e) {
            throw e;
        }
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
