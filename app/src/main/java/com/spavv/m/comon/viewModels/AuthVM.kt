package com.spavv.m.comon.viewModels

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.spavv.m.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

open class AuthVM(private val sharedPreferences: SharedPreferences) : ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance();

    lateinit var googleSignInClient : GoogleSignInClient;

    private val _authState = MutableLiveData<AuthState>();
    val authState: LiveData<AuthState> = _authState;

    init {
        checkAuthState()

    }

    fun createGoogleSignInClient(context: Context){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("873564041049-df4rtdn53pvua93aql8vl7e9babk0khq.apps.googleusercontent.com") // Lấy ID Token đúng
            .requestEmail()
            .build()
        googleSignInClient =  GoogleSignIn.getClient(context, gso)
    }

    fun getSignInIntent(): Intent {
        if (!this::googleSignInClient.isInitialized) {
            throw IllegalStateException("GoogleSignInClient chưa được khởi tạo. Gọi createGoogleSignInClient(context) trước!")
        }
        return googleSignInClient.signInIntent
    }
    fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        viewModelScope.launch {
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken
                //Log.d("GoogleSignIn", "ID Token: $idToken")

                if (idToken != null) {
                    // Gửi token lên server để xác thực Firebase
                    signInWithGoogle(idToken)
                }
            } catch (e: ApiException) {
               Log.e("GoogleSignIn", "Đăng nhập thất bại: ${e.statusCode}")
            }
        }
    }

    private fun signInWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //Log.d("GoogleSignIn", "Đăng nhập Firebase thành công!")
                    _authState.value = AuthState.Authenticated
                    //save token
                    viewModelScope.launch {
                        val token = getToken()
                        sharedPreferences.edit().apply {
                            putString("tokenString", token)
                            apply()
                        }
                    }
                } else {
                   // Log.e("GoogleSignIn", "Lỗi khi đăng nhập Firebase: ${task.exception?.message}")
                    _authState.value = AuthState.Error("Đăng nhập thất bại")
                }
            }
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