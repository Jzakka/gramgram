package com.ll.gramgram.boundedContext.instaMember.eventListener;

import com.ll.gramgram.base.event.EventAfterFromInstaMemberChangeGender;
import com.ll.gramgram.base.event.EventAfterLike;
import com.ll.gramgram.base.event.EventAfterModifyAttractiveType;
import com.ll.gramgram.base.event.EventBeforeCancelLike;
import com.ll.gramgram.boundedContext.instaMember.service.InstaMemberService;
import com.ll.gramgram.boundedContext.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InstaMemberEventListener {
    private final InstaMemberService instaMemberService;
    private final NotificationService notificationService;

    @EventListener
    @Transactional
    public void listen(EventAfterModifyAttractiveType event) {
        instaMemberService.whenAfterModifyAttractiveType(event.getLikeablePerson(), event.getOldAttractiveTypeCode());
        notificationService.notify(event.getLikeablePerson(), event.getOldAttractiveTypeCode());
    }

    @EventListener
    public void listen(EventAfterLike event) {
        instaMemberService.whenAfterLike(event.getLikeablePerson());
        notificationService.notify(event.getLikeablePerson());
    }

    @EventListener
    public void listen(EventBeforeCancelLike event) {
        instaMemberService.whenBeforeCancelLike(event.getLikeablePerson());
    }

    @EventListener
    public void listen(EventAfterFromInstaMemberChangeGender event) {
        instaMemberService.whenAfterFromInstaMemberChangeGender(event.getInstaMember(), event.getOldGender());
    }
}