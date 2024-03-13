package com.example.v3.profile.adapter.inbound.web

import com.example.v3.common.Nickname
import com.example.v3.common.api.ApiResponse
import com.example.v3.common.util.toUUID
import com.example.v3.member.domain.MemberUid
import com.example.v3.profile.application.port.inbound.CreateProfileCommand
import com.example.v3.profile.application.port.inbound.ProfileUseCase
import com.example.v3.profile.domain.Profile
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/api/v1/profiles")
class ProfileController(private val profileUseCase: ProfileUseCase) {
    @PostMapping
    fun createProfile(@RequestBody @Validated request: CreateProfileRequest) =
        request
            .toCommand()
            .let(profileUseCase::createProfile)
            .toCreateProfileResponse()
            .toApiResponse()
}

data class CreateProfileRequest(
    val memberUid: String,
    @NotEmpty val nickname: String
) {
    fun toCommand() =
        CreateProfileCommand(
            memberUid = MemberUid(memberUid.toUUID()),
            nickname = Nickname(nickname)
        )
}

data class CreateProfileResponse(val profileUid: String) {
    fun toApiResponse() = ApiResponse.success(data = this)
}

// Domain 내부 코드를 조작하지 않으면서, 좌에서 우로 읽히는 코드
private fun Profile.toCreateProfileResponse() =
    CreateProfileResponse(profileUid = this.uid.toString())

data class UpdateProfileImageRequest(@Length(min = 3, max = 12) val profileImageUrl: String)
