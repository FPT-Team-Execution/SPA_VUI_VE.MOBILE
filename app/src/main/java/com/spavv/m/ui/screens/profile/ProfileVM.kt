package com.spavv.m.ui.screens.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.dataSources.UserDataSource
import com.spavv.m.data.models.User
import kotlinx.coroutines.launch

class ProfileVM(
    private val userDataSource: UserDataSource
) : ViewModel() {

    var isLoading = mutableStateOf<Boolean>(false)

    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    private fun updateUser(user: User?) {
        _user.value = user
    }

    fun fetchUser(userId: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val user = userDataSource.getUser(userId)
                updateUser(user)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            isLoading.value = false
        }
    }
}