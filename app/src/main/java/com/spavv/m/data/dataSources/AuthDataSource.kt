package com.spavv.m.data.dataSources

import com.spavv.m.data.api.FirebaseApi

interface AuthDataSource {
    fun LoginByUsername(username: String, password: String);
}

class AuthDataSourceImpl(firebaseApi: FirebaseApi) : AuthDataSource  {
    override fun LoginByUsername(username: String, password: String) {

    }

}