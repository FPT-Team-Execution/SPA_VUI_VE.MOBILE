package com.spavv.m.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class ProfileVM : ViewModel() {
    private val _profileData = mutableStateOf("Default Profile Data")
    val profileData: State<String> get() = _profileData

    fun updateProfileData(newData: String) {
        _profileData.value = newData
    }
}