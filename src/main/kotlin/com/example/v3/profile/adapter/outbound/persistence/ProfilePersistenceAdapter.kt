package com.example.v3.profile.adapter.outbound.persistence

import com.example.v3.member.domain.MemberUid
import com.example.v3.profile.application.port.outbound.LoadProfileOwnerPort
import com.example.v3.profile.application.port.outbound.LoadProfilePort
import com.example.v3.profile.application.port.outbound.UpdateProfileOwnerPort
import com.example.v3.profile.domain.Profile
import com.example.v3.profile.domain.ProfileOwner
import com.example.v3.profile.domain.ProfileUid
import org.springframework.stereotype.Component

// MongoDB 등의 영속성 DB 에서 쿼리
@Component
class ProfilePersistenceAdapter : LoadProfilePort, LoadProfileOwnerPort, UpdateProfileOwnerPort {
    override fun loadProfileOwner(memberUid: MemberUid): ProfileOwner {
        TODO("memberUid로 ProfileOwner를 DB에서 조회한다.")
    }

    override fun loadProfile(profileUid: ProfileUid): Profile {
        TODO("profileUid로 Profile을 조회한다.")
    }

    override fun registerProfile(profileOwner: ProfileOwner): ProfileOwner {
        TODO("ProfileOwner의 memberUid를 갖는 Profile DB 객체를 저장하고, ProfileOwner를 반환한다.")
    }

    override fun updateProfile(profileOwner: ProfileOwner) {
        TODO("ProfileOwner의 Profile을 수정한다.")
    }
}
