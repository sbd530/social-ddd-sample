package com.example.v3.profile.adapter.outbound.event

import com.example.v3.profile.domain.ProfileOwner
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

data class ProfileCreatedEvent(val profileOwner: ProfileOwner)

@Component
class ProfileEventHandler {
    @Async
    @EventListener
    fun handle(event: ProfileCreatedEvent) {
        // ProfileCreatedEvent 처리. 단, persistence 등과 관계 없는 행위만
    }
}
