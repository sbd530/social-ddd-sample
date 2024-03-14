package com.example.v3.profile.adapter.outbound.persistence

import com.example.v3.common.Image
import com.example.v3.member.domain.MemberUid
import com.example.v3.profile.application.port.outbound.LoadProfilePort
import com.example.v3.profile.application.port.outbound.SaveProfilePort
import com.example.v3.profile.domain.Profile
import com.example.v3.profile.domain.ProfileOwner
import com.example.v3.profile.domain.ProfileUid
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

// MongoDB 등의 영속성 DB 에서 쿼리
@Component
class ProfilePersistenceAdapter : LoadProfilePort, SaveProfilePort {
    override fun loadProfileOwner(memberUid: MemberUid): ProfileOwner {
        TODO("memberUid로 ProfileOwner를 DB에서 조회한다.")
    }

    override fun loadProfile(profileUid: ProfileUid): Profile {
        TODO("profileUid로 Profile을 조회한다.")
    }

    override fun loadProfileByProfileOwner(profileOwner: ProfileOwner): Profile? {
        TODO("profileOwner가 갖는 Profile을 찾는다.")
    }

    @Transactional
    override fun registerProfile(profile: Profile): Profile {
        TODO("Profile DB 객체를 저장하고, Profile을 반환한다.")
    }

    override fun updateImage(image: Image) {
        TODO("Profile의 Image을 수정한다.")
    }
}
