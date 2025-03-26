package com.spavv.m.ui.components.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.spavv.m.data.models.Product
import com.spavv.m.ui.theme.DarkColor
import com.spavv.m.ui.theme.GreyColor
import com.spavv.m.ui.theme.PrimaryColor

@Composable
fun ProductCard(
    product: Product,
    primaryColor: Color,
    backgroundColor: Color,
    textColor: Color,
    secondaryTextColor: Color,
    borderColor: Color,
    onClick: () -> Unit = {},
) {
    val imageProduct: Any = product.imageUrl.ifEmpty { "https://placehold.co/191x100/png" }

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = backgroundColor,
        border = BorderStroke(1.dp, borderColor.copy(alpha = 0.3f)),
        modifier = Modifier
            .width(200.dp)
            .clickable { /* Handle product click */ }
    ) {
        Column(
            modifier = Modifier.padding(12.dp).clickable {
                onClick()
            }
        ) {
            // Product image
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageProduct)
                        .crossfade(true)
                        .build()
                ),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Product details
            Text(
                text = product.name,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.description,
                style = TextStyle(fontSize = 14.sp),
                color = secondaryTextColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Price and buy option
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${product.price}K",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    color = primaryColor
                )

                Icon(
                    imageVector = Icons.Default.ShoppingBag,
                    contentDescription = "Buy Icon",
                    tint = primaryColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

