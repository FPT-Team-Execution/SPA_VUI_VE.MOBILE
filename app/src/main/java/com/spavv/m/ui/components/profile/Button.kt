import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LineDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)

        )
    }
}

@Composable
fun AboutAppButton() {
    Button(
        onClick = {
            // Navigate to About App
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        border = BorderStroke(2.dp, Color.White),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFbcf9a5), Color(0xFF7fdcf9))
                ),
                shape = RoundedCornerShape(16.dp)
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Article,
                contentDescription = "Icon",
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = "Giới thiệu về Spa Vui Vẻ",
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ButtonTop(modifier: Modifier = Modifier, text: String, customIcon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = customIcon,
                    contentDescription = "Icon",
                    modifier = Modifier.padding(end = 16.dp),
                )
                Text(text = text, modifier = Modifier.weight(0.5f), fontWeight = FontWeight.Normal, fontSize = 16.sp)
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Navigate Icon",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ButtonMiddle(modifier: Modifier = Modifier, text: String, customIcon: ImageVector, onClick: () -> Unit) {

    Column {
        LineDivider()
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(0.dp),
            modifier = modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = customIcon,
                        contentDescription = "Icon",
                        modifier = Modifier.padding(end = 16.dp),
                    )
                    Text(text = text, modifier = Modifier.weight(0.5f), fontWeight = FontWeight.Normal, fontSize = 16.sp)
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Navigate Icon",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ButtonBottom(modifier: Modifier = Modifier, text: String, customIcon: ImageVector, onClick: () -> Unit) {
    Column {
        LineDivider()
        Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = customIcon,
                    contentDescription = "Icon",
                    modifier = Modifier.padding(end = 16.dp),
                )
                Text(text = text, modifier = Modifier.weight(0.5f), fontWeight = FontWeight.Normal, fontSize = 16.sp)
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Navigate Icon",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    } }

}

@Composable
fun LogoutButton() {
    Button(
        onClick = {
            // Logout
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Logout,
            contentDescription = "Icon",
            modifier = Modifier.padding(end = 16.dp),
            tint = Color.Blue // Add the tint parameter here
        )
        Text(text = "Đăng xuất", color = Color.Blue, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
    }
}