package com.spavv.m.di


import android.content.Context
import com.spavv.m.data.api.FirebaseApi
import com.spavv.m.data.dataSources.AuthDataSource
import com.spavv.m.data.dataSources.AuthDataSourceImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    //* Apis
    val fireBaseApi : FirebaseApi
    //* Data sources
    val authDataSource : AuthDataSource
}

class AppModuleImpl(appContext: Context) : AppModule {
    private val firebaseUrl : String = "";

    override val fireBaseApi: FirebaseApi by lazy {
        Retrofit.Builder()
            .baseUrl(firebaseUrl)
            .addConverterFactory(GsonConverterFactory.create()) // Important: Add a converter factory!
            .build()
            .create(FirebaseApi::class.java)

    }

    override val authDataSource: AuthDataSource by lazy {
        AuthDataSourceImpl(fireBaseApi)
    }
}