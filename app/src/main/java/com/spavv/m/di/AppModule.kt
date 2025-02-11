package com.spavv.m.di


import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.spavv.m.data.api.CategoryApi
import com.spavv.m.data.api.FirebaseApi
import com.spavv.m.data.api.ProductApi
import com.spavv.m.data.api.SkinTestApi
import com.spavv.m.data.dataSources.AuthDataSource
import com.spavv.m.data.dataSources.AuthDataSourceImpl
import com.spavv.m.data.dataSources.ProductDataSource
import com.spavv.m.data.dataSources.ProductDataSourceImpl
import com.spavv.m.data.dataSources.SkinTestDataSource
import com.spavv.m.data.dataSources.SkinTestDataSourceImp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    //* Constant
    //val navController : NavController
    //* Apis
    val fireBaseApi: FirebaseApi
    val categoryApi: CategoryApi
    val productApi: ProductApi
    val skinTestApi: SkinTestApi


    //* Data sources
    val authDataSource: AuthDataSource
    val productDataSource: ProductDataSource
    val skinTestDataSource: SkinTestDataSource
}

class AppModuleImpl(
    appContext: Context,
) : AppModule {
    private val firebaseUrl: String = "https://something-demo";
    private val baseUrl: String = "https://localhost";

    override val fireBaseApi: FirebaseApi by lazy {
        Retrofit.Builder()
            .baseUrl(firebaseUrl)
            .addConverterFactory(GsonConverterFactory.create()) // Important: Add a converter factory!
            .build()
            .create(FirebaseApi::class.java)

    }
    override val productApi: ProductApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()) // Important: Add a converter factory!
            .build()
            .create(ProductApi::class.java)

    }

    override val categoryApi: CategoryApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()) // Important: Add a converter factory!
            .build()
            .create(CategoryApi::class.java)

    }

    override val skinTestApi: SkinTestApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()) // Important: Add a converter factory!
            .build()
            .create(SkinTestApi::class.java)
    }

    override val authDataSource: AuthDataSource by lazy {
        AuthDataSourceImpl(fireBaseApi)
    }

    override val productDataSource: ProductDataSource by lazy {
        ProductDataSourceImpl(productApi)
    }
    override val skinTestDataSource: SkinTestDataSource by lazy {
        SkinTestDataSourceImp(skinTestApi)
    }

}