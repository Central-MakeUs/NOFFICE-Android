package com.easyhz.noffice.data.member.repository.user

interface UserRepository {
    suspend fun getIsFirstRun(): Result<Boolean>
    suspend fun setIsFirstRun(newValue: Boolean): Result<Unit>
}