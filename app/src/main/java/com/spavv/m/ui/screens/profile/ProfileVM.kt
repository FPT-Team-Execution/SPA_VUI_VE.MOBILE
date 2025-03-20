package com.spavv.m.ui.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.dataSources.UserDataSource
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.User
import com.spavv.m.data.models.base.Paginate
import kotlinx.coroutines.launch

class ProfileVM(
    private val userDataSource: UserDataSource
) : ViewModel() {

    var isLoading = mutableStateOf<Boolean>(false)

    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    private fun updateUser(user: Paginate<Product>?) {
        _user.value = user
    }

    fun fetchUser(userId: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = userDataSource.getUser(userId = userId)
                if (response.isSuccessful) {
                    updateUser(response.body()?.data)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            isLoading.value = false
        }
    }
}