package com.example.v3.profile.application.port.outbound

import com.example.v3.member.domain.MemberUid
import com.example.v3.profile.domain.ProfileOwner

interface LoadProfileOwnerPort {
    fun loadProfileOwner(memberUid: MemberUid): ProfileOwner
}