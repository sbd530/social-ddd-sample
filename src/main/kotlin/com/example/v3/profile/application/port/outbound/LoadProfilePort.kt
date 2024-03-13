package com.example.v3.profile.application.port.outbound

import com.example.v3.profile.domain.Profile
import com.example.v3.profile.domain.ProfileUid

interface LoadProfilePort {
    fun loadProfile(profileUid: ProfileUid): Profile
}