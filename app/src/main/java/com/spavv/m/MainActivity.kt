package com.spavv.m

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.spavv.m.ui.theme.SpaVuiVeTheme


val LocalNavigation = staticCompositionLocalOf<NavHostController> { error("Not provided") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                SpaVuiVeTheme {
                    //create new rememberNavController instance
                    val navController = rememberNavController()
                    CompositionLocalProvider(LocalNavigation provides navController) {
                        MyAppNavigation(modifier = Modifier.fillMaxSize().padding(16.dp))
                    }
                }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSpace() {

}
