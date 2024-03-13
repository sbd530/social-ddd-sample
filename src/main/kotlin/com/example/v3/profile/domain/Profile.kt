package com.example.v3.profile.domain

import com.example.v3.common.*
import java.util.UUID

data class Profile(
    val uid: ProfileUid,
    val nickname: Nickname,
    val image: Image?,
    val createdAt: Timestamp,
    val updatedAt: Timestamp?
)

@JvmInline
value class ProfileUid(val uuid: UUID)
