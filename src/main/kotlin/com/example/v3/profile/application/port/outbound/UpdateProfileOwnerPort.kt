package com.example.v3.profile.application.port.outbound

import com.example.v3.profile.domain.ProfileOwner

interface UpdateProfileOwnerPort {
    fun registerProfile(profileOwner: ProfileOwner): ProfileOwner
    fun updateProfile(profileOwner: ProfileOwner)
}