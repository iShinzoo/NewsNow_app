package com.example.newsnow.domain.UseCases

import com.example.newsnow.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(): Flow<Boolean>{
        return localUserManager.ReadAppEntry()
    }
}