package com.spavv.m.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.spavv.m.data.models.Product

@Composable
fun ProductItem(modifier: Modifier = Modifier, product: Product) {
    val imageProduct: Any = product.imageUrl.ifEmpty { "https://placehold.co/191x100/png" }

    Box(
        modifier = modifier
            .width(191.dp)
            .height(167.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageProduct),
            contentDescription = product.name,
            modifier = Modifier
                .size(width = 191.dp, height = 100.dp)
                .clip(RoundedCornerShape(10.dp))

        )

        Column(
            modifier = Modifier
                .padding(top = 111.dp, start = 5.dp)
        ) {
            androidx.compose.material3.Text(
                text = product.name,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
                color = Color.Black
            )
            androidx.compose.material3.Text(
                text = product.description,
                style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Medium),
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
            )

            androidx.compose.material3.Text(
                text = "${product.price}K",
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),

                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Icon(
            imageVector = Icons.Default.ShoppingBag,
            contentDescription = "Buy Icon",
            tint = Color.Black,
            modifier = Modifier.size(25.dp)
                .align(Alignment.BottomEnd)
                .padding(end = 10.dp, bottom = 10.dp)
        )
    }
}

@Preview(widthDp = 191, heightDp = 167)
@Composable
private fun Preview() {

}
