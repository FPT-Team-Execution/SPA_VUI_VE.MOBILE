package com.spavv.m

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.spavv.m.ui.theme.SpaVuiVeTheme
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


val LocalNavigation = staticCompositionLocalOf<NavHostController> { error("Not provided") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disableSSLCertificateChecking()
        enableEdgeToEdge()
        setContent {
                SpaVuiVeTheme {
                    //create new rememberNavController instance
                    val navController = rememberNavController()
                    CompositionLocalProvider(LocalNavigation provides navController) {
                        MyAppNavigation(modifier = Modifier
                            .fillMaxSize()
                            //safe area
                            //! DO NOT REMOVE
                            .statusBarsPadding())
                    }
                }
        }
    }
}

private fun disableSSLCertificateChecking() {
    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return emptyArray()
        }

        @Throws(CertificateException::class)
        override fun checkClientTrusted(arg0: Array<X509Certificate?>?, arg1: String?) {
            // Not implemented
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(arg0: Array<X509Certificate?>?, arg1: String?) {
            // Not implemented
        }
    })

    try {
        val sc = SSLContext.getInstance("TLS")

        sc.init(null, trustAllCerts, SecureRandom())

        HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
    } catch (e: KeyManagementException) {
        e.printStackTrace()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSpace() {

}
