package com.easyhz.noffice.domain.notification.usecase

import com.easyhz.noffice.core.common.base.BaseUseCase
import com.easyhz.noffice.data.notification.repository.messaging.CloudMessagingRepository
import javax.inject.Inject

class RegisterMessagingTokenUseCase @Inject constructor(
    private val cloudMessagingRepository: CloudMessagingRepository,
) : BaseUseCase<Unit, Unit>() {
    override suspend fun invoke(param: Unit): Result<Unit> = runCatching {
        val token = getMessagingToken().getOrThrow()
        registerMessagingToken(token)
    }

    private suspend fun getMessagingToken(): Result<String> {
        return cloudMessagingRepository.getToken()
    }

    private suspend fun registerMessagingToken(token: String): Result<Unit> {
        return cloudMessagingRepository.registerToken(token)
    }
}