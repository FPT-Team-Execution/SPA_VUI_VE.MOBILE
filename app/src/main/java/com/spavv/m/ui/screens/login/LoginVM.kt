package com.spavv.m.ui.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class LoginVM : ViewModel() {
    //* Value to change data
    private var _email = mutableStateOf<String>("");
    //* Value to hold data - view only
    val email: State<String> get() = _email;
    //* Fun used to change data on view
    fun updateEmail(value: String){
        print("Change")
        _email.value = value;
    }

    //* Value to change data
    private var _password = mutableStateOf("");
    //* Value to hold data - view only
    val password: State<String> get() = _password;
    //* Fun used to change data on view
    fun updatePassword(value: String){
        _password.value = value;
    }



}