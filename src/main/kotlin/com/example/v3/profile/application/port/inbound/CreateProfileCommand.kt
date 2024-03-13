package com.example.v3.profile.application.port.inbound

import com.example.v3.common.Nickname
import com.example.v3.member.domain.MemberUid

data class CreateProfileCommand(
    val memberUid: MemberUid,
    val nickname: Nickname
)