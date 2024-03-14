package com.example.v3.profile.application.service

import com.example.v3.common.Image
import com.example.v3.common.util.currentTimestamp
import com.example.v3.common.util.randomUUID
import com.example.v3.profile.application.port.inbound.CreateProfileCommand
import com.example.v3.profile.application.port.inbound.ProfileUseCase
import com.example.v3.profile.application.port.outbound.LoadProfilePort
import com.example.v3.profile.application.port.outbound.PublishProfileEventPort
import com.example.v3.profile.application.port.outbound.SaveProfilePort
import com.example.v3.profile.domain.Profile
import com.example.v3.profile.domain.ProfileOwner
import com.example.v3.profile.domain.ProfileUid
import org.springframework.stereotype.Service

@Service
class ProfileCommandService(
    private val loadProfilePort: LoadProfilePort,
    private val saveProfilePort: SaveProfilePort,
    private val publishProfileEventPort: PublishProfileEventPort
) : ProfileUseCase {
    override fun createProfile(command: CreateProfileCommand): Profile {
        val owner = loadProfilePort.loadProfileOwner(memberUid = command.memberUid)
        validateProfileExist(owner)
        val profile = Profile(
            uid = ProfileUid(uuid = randomUUID()),
            owner = owner,
            nickname = command.nickname,
            image = null,
            createdAt = currentTimestamp(),
            updatedAt = null
        )
        saveProfilePort.registerProfile(profile = profile)
        publishProfileEventPort.publishProfileCreatedEvent(profileOwner = owner)
        return profile
    }

    private fun validateProfileExist(profileOwner: ProfileOwner) {
        loadProfilePort.loadProfileByProfileOwner(profileOwner = profileOwner)
            ?.also { throw ProfileAlreadyExistsException() }
    }

    override fun updateImage(profileUid: ProfileUid, image: Image): Profile {
        TODO("Not yet implemented")
    }
}
