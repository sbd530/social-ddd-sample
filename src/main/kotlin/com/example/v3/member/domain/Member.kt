package com.example.v3.member.domain

import java.util.*

data class Member(
    val uid: MemberUid,
    val createdAt: Long
)

@JvmInline
value class MemberUid(val uuid: UUID)
