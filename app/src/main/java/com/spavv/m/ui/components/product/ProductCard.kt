import android.icu.text.NumberFormat
import android.icu.util.Currency
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.spavv.m.data.models.Product
import com.spavv.m.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onAddToCart: () -> Unit = {},
    isFavorite: Boolean = false,
    onToggleFavorite: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(340.dp)
            .padding(8.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = Color(0x1A000000)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Image container with favorite button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Color(0xFFF8F8F8))
            ) {
                // Product image
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )

                // Stock status indicator
                if ((product.stockQuantity ?: 0) > 0) {
                    Badge(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "In Stock",
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                } else {
                    Badge(
                        containerColor = Color.Gray,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Out of Stock",
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                }

//                // Favorite button
//                IconButton(
//                    onClick = onToggleFavorite,
//                    modifier = Modifier
//                        .align(Alignment.TopEnd)
//                        .padding(8.dp)
//                        .size(32.dp)
//                        .clip(CircleShape)
//                        .background(Color.White.copy(alpha = 0.8f))
//                ) {
//                    Icon(
//                        imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
//                        contentDescription = "Favorite",
//                        tint = if (isFavorite) Color.Red else Color.Gray,
//                        modifier = Modifier.size(18.dp)
//                    )
//                }
            }

            // Product details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Brand and category badges
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SuggestionChip(
                        onClick = { },
                        label = {
                            Text(
                                text = product.brand?.name ?: "Unknown",
                                fontSize = 10.sp,
                                color = PrimaryColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = PrimaryColor.copy(alpha = 0.1f)
                        ),
                        border = null,
                        modifier = Modifier.height(22.dp)
                    )

                    SuggestionChip(
                        onClick = { },
                        label = {
                            Text(
                                text = product.category?.name ?: "Unknown",
                                fontSize = 10.sp,
                                color = Color.DarkGray,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = Color(0xFFEEEEEE)
                        ),
                        border = null,
                        modifier = Modifier.height(22.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Product name
                Text(
                    text = product.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.weight(1f))

                // Price and add to cart row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Price
                    Text(
                        text = formatPrice(product.price),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryColor
                    )

//                    // Add to cart button
//                    IconButton(
//                        onClick = onAddToCart,
//                        modifier = Modifier
//                            .size(36.dp)
//                            .clip(CircleShape)
//                            .background(PrimaryColor)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.ShoppingCart,
//                            contentDescription = "Add to Cart",
//                            tint = Color.White,
//                            modifier = Modifier.size(18.dp)
//                        )
//                    }
                }
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