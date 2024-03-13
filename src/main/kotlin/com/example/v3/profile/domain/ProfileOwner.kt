package com.example.v3.profile.domain

import com.example.v3.member.domain.MemberUid

data class ProfileOwner(
    val memberUid: MemberUid,
    var profile: Profile?
) {
    fun hasProfile(): Boolean = this.profile != null

    fun registerProfile(profile: Profile) = apply { this.profile = profile }
}
