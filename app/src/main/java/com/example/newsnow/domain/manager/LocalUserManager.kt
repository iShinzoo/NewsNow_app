package com.example.newsnow.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun SaveAppEntry()

    fun ReadAppEntry() : Flow<Boolean>

}