package com.example.v3.profile.application.port.inbound

import com.example.v3.common.Image
import com.example.v3.profile.domain.Profile
import com.example.v3.profile.domain.ProfileUid

interface ProfileUseCase {
    fun createProfile(command: CreateProfileCommand): Profile
    fun updateImage(profileUid: ProfileUid, image: Image): Profile
}
