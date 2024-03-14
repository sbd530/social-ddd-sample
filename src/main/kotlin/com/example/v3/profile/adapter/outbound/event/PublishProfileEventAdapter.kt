package com.example.v3.profile.adapter.outbound.event

import com.example.v3.profile.application.port.outbound.PublishProfileEventPort
import com.example.v3.profile.domain.ProfileOwner
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

/**
 * Event publishing 을 Spring ApplicationEventPublisher 로 할지,
 * Kafka 등의 Queue 에 publish 할지 여기서 결정 된다.
 */
@Component
class PublishProfileEventAdapter(
    private val applicationEventPublisher: ApplicationEventPublisher // Spring
) : PublishProfileEventPort {
    override fun publishProfileCreatedEvent(profileOwner: ProfileOwner) {
        applicationEventPublisher.publishEvent(ProfileCreatedEvent(profileOwner))
    }
}
