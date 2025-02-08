package com.spavv.m

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spavv.m.comon.viewModels.AuthVM
import com.spavv.m.ui.theme.SpaVuiVeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpaVuiVeTheme {
                MyAppNavigation(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), authVM = viewModel<AuthVM>())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSpace() {

}
