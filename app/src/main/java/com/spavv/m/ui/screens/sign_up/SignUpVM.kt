package com.spavv.m.ui.screens.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.spavv.m.comon.viewModels.AuthVM

class SignUpVM : ViewModel() {
    //* Value to change data
    private var _email = mutableStateOf<String>("");
    //* Value to hold data - view only
    val email: State<String> get() = _email;
    //* Fun used to change data on view
    fun updateEmail(value: String){
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

    private var _passwordVisible = mutableStateOf<Boolean>(false);
    val passwordVisible: State<Boolean> get() = _passwordVisible;
    fun updatePasswordVisible(value: Boolean){
        _passwordVisible.value = value;
    }
}