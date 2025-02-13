import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.spavv.m.data.models.Product

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier, onClick: ()-> Unit) {


    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onClick
    ) {
        Column(modifier = modifier) {
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = product.name,
                modifier = modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = modifier.height(8.dp))
            Column(modifier = modifier.padding(8.dp)) {
                Text(text = product.name, fontSize = 18.sp, color = Color.Black)
                Spacer(modifier = modifier.height(8.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = product.brand?.name ?: "Unknown",
                        color = Color.White,
                        fontSize = 12.sp, // Kích thước chữ
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFFFC0CB))
                            .padding(4.dp)
                    )
                    Text(
                        text = product.category?.name ?: "Unknown",
                        color = Color.Black,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFADD8E6))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                }
                Spacer(modifier = modifier.height(8.dp))
                Text(text = "${product.stockQuantity ?: "N/A"} in stock")

                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = formatPrice(product.price),
                    fontSize = 18.sp,
                    color = Color.Red
                )



            }
        }
    }
}

fun formatPrice(price: Double): String {
    val format = NumberFormat.getCurrencyInstance().apply {
        currency = Currency.getInstance("USD")
    }
    return format.format(price)
}

