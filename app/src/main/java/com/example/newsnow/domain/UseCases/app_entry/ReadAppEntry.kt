package com.example.newsnow.domain.UseCases.app_entry

import com.example.newsnow.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
     operator fun invoke(): Flow<Boolean>{
        return localUserManager.ReadAppEntry()
    }
}