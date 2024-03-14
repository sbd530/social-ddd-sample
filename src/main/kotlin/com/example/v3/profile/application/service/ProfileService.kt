package com.example.v3.profile.application.service

import com.example.v3.common.Image
import com.example.v3.common.util.currentTimestamp
import com.example.v3.common.util.randomUUID
import com.example.v3.profile.application.port.inbound.CreateProfileCommand
import com.example.v3.profile.application.port.inbound.ProfileUseCase
import com.example.v3.profile.application.port.outbound.LoadProfileOwnerPort
import com.example.v3.profile.application.port.outbound.PublishProfileEventPort
import com.example.v3.profile.application.port.outbound.UpdateProfileOwnerPort
import com.example.v3.profile.domain.Profile
import com.example.v3.profile.domain.ProfileUid
import org.springframework.stereotype.Service

@Service
class ProfileService(
    private val loadProfileOwnerPort: LoadProfileOwnerPort,
    private val updateProfileOwnerPort: UpdateProfileOwnerPort,
    private val publishProfileEventPort: PublishProfileEventPort
) : ProfileUseCase {
    override fun createProfile(command: CreateProfileCommand): Profile {
        val owner = loadProfileOwnerPort.loadProfileOwner(memberUid = command.memberUid)
        if (owner.hasProfile()) throw ProfileAlreadyExistsException()
        val profile = Profile(
            uid = ProfileUid(uuid = randomUUID()),
            nickname = command.nickname,
            image = null,
            createdAt = currentTimestamp(),
            updatedAt = null
        )
        owner.registerProfile(profile)
        updateProfileOwnerPort.registerProfile(profileOwner = owner)
        publishProfileEventPort.publishProfileCreatedEvent(profileOwner = owner)
        return profile
    }

    override fun updateImage(profileUid: ProfileUid, image: Image): Profile {
        TODO("Not yet implemented")
    }
}
