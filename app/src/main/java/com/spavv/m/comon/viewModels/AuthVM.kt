package com.spavv.m.comon.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

open class AuthVM : ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance();

    private val _authState = MutableLiveData<AuthState>();
    val authState: LiveData<AuthState> = _authState;

    fun checkAuthState(){
        if(auth.currentUser == null){
            _authState.value = AuthState.Unauthenticated;
        }else{
            _authState.value = AuthState.Authenticated;
        }
    }

    fun login(email: String, password: String){

        _authState.value = AuthState.Authenticated;
//        if(email.isEmpty() || password.isEmpty()){
//            _authState.value = AuthState.Error("Email hoặc mật khẩu chưa chính xác");
//            return;
//        }
//
//        _authState.value = AuthState.Loading;
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//            taskRs -> if(taskRs.isSuccessful) {
//                _authState.value = AuthState.Authenticated;
//                }else
//                {
//                    _authState.value = AuthState.Error(taskRs.exception?.message?: "Thực hiện đăng nhập thất bại");
//                }
//        }
    }
    fun signup(email: String, password: String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email hoặc mật khẩu chưa chính xác");
            return;
        }

        _authState.value = AuthState.Loading;
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                    taskRs -> if(taskRs.isSuccessful) {
                _authState.value = AuthState.Authenticated;
            }else
            {
                _authState.value = AuthState.Error(taskRs.exception?.message?: "Thực hiện đăng nhập thất bại");
            }
            }
    }

    fun signOut(){
       auth.signOut();
        _authState.value = AuthState.Unauthenticated
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    class Error(val message : String) : AuthState()
}