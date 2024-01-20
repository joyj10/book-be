package com.won.bookappapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 이메일 발송 서비스 클래스
 * - TODO 실제 이메일 발송 기능 추가 필요
 */
@Slf4j
@Service
public class EmailSenderService {
    public void sendWelcomeMail(String email, String name) {
        log.info("email send : {}, {}", email, name);
    }
}
