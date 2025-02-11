package com.spavv.m.di


import android.content.Context
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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.net.ssl.SSLSocketFactory;


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
    private val baseUrl: String = "https://10.0.2.2:7000/";

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
            .client(getUnsafeOkHttpClient())
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

    private fun getUnsafeOkHttpClient(): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
            )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

            val builder: OkHttpClient.Builder = OkHttpClient.Builder()

            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(HostnameVerifier { hostname, session -> true })

            val okHttpClient: OkHttpClient = builder.build()
            return okHttpClient
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}