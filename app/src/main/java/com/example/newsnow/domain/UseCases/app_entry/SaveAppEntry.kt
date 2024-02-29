package com.example.newsnow.domain.UseCases.app_entry

import com.example.newsnow.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.SaveAppEntry()
    }
}