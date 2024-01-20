package com.won.bookappapi.service.eventhandler;

import com.won.bookappapi.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserEventHandler {
    private final EmailSenderService emailSenderService;

    @TransactionalEventListener
    public void userJoinEventListener(UserJoinEvent event) {
        emailSenderService.sendWelcomeMail(event.getUser().getEmail(), event.getUser().getName());
    }
}
