package com.example.v3.profile.application.port.outbound

import com.example.v3.profile.domain.ProfileOwner

interface PublishProfileEventPort {
    fun publishProfileCreatedEvent(profileOwner: ProfileOwner)
}
