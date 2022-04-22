package io.dmitrikonnov;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            System.out.println("Email service thread: "+ Thread.currentThread().getName());
            if (Thread.currentThread().getName().equals("task-1")){
                System.out.println("Stopped");
                LockSupport.parkNanos(900000000);
                System.out.println("Stopped");
                LockSupport.parkNanos(900000000);
                System.out.println("Stopped");
                LockSupport.parkNanos(900000000);
                System.out.println("Stopped");
                LockSupport.parkNanos(900000000);
                System.out.println("Stopped");
                LockSupport.parkNanos(900000000);
                LockSupport.parkNanos(900000000);
                LockSupport.parkNanos(900000000);
            }
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(email,true);
            helper.setTo(to);
            helper.setSubject("Look here");
            helper.setFrom("dmitri.v.konnov@gmail.com");
            System.out.println(Thread.currentThread().getName());
            mailSender.send(mimeMessage);
        }catch (MessagingException e) {
            log.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
