package com.example.java5.demo.servicelmp;

import javax.mail.internet.MimeMessage;
import javax.sound.sampled.AudioFormat.Encoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.java5.demo.service.MailService;

@Service
public class MailServiceImp implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendMail(String to, String subject, String content) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setSubject(subject);
            helper.setFrom("xuanquy2433@gmail.com");
            helper.setTo(to);

            boolean html = true;
            helper.setText(content, html);

            javaMailSender.send(msg);
        } catch (Exception e) {
            System.err.println("erroe send mail " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
