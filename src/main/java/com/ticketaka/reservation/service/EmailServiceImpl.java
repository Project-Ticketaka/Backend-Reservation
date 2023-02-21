package com.ticketaka.reservation.service;

import com.ticketaka.reservation.dto.mail.EmailMessageDTO;
import com.ticketaka.reservation.dto.request.ReservationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private  final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendMail(ReservationDTO reservationDTO) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        Context context = new Context();
        context.setVariable("data", reservationDTO);
        String message = templateEngine.process("mail/mail-ticket", context);
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(reservationDTO.getReservationPoster()); // 메일 수신자
            System.out.println(reservationDTO.getReservationPoster());
            mimeMessageHelper.setSubject("email Title"); // 메일 제목
            mimeMessageHelper.setText(message, true); // 메일 본문 내용, HTML 여부

            File file = new ClassPathResource("static/images/bg_ticket.png").getFile();
            FileSystemResource fsr = new FileSystemResource(file);

            mimeMessageHelper.addInline("bg_ticket.png", fsr);
            javaMailSender.send(mimeMessage);

            log.info("Success Send Email");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
