package com.example.v3.profile.application.port.outbound

import com.example.v3.common.Image
import com.example.v3.profile.domain.Profile

interface SaveProfilePort {
    fun registerProfile(profile: Profile): Profile
    fun updateImage(image: Image)
}
