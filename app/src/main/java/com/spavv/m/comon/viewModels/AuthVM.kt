package com.spavv.m.comon.viewModels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

open class AuthVM(private val sharedPreferences: SharedPreferences) : ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance();

    private val _authState = MutableLiveData<AuthState>();
    val authState: LiveData<AuthState> = _authState;

    init {
        checkAuthState()
    }

    suspend fun getToken(): String? {
        return auth.currentUser?.getIdToken(true)?.await()?.token
    }
    private fun saveTokenToPrefs(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("tokenString", token).apply()
    }

    fun checkAuthState() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated

            viewModelScope.launch {
                val token = getToken()
                sharedPreferences.edit().apply {
                    putString("tokenString", token)
                    apply()
                }
            }
        }
    }

    fun login(email: String, password: String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email hoặc mật khẩu chưa chính xác");
            return;
        }

        _authState.value = AuthState.Loading;
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
            taskRs -> if(taskRs.isSuccessful) {
                _authState.value = AuthState.Authenticated;
                //save token
                viewModelScope.launch {
                    val token = getToken()
                    sharedPreferences.edit().apply {
                        putString("tokenString", token)
                        apply()
                    }
                }
                }else
                {
                    _authState.value = AuthState.Error(taskRs.exception?.message?: "Thực hiện đăng nhập thất bại");
                }
        }
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