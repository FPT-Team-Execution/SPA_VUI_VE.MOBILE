import java.util.*

data class Promotion(
    val promotionId: String,
    val code: String,
    val name: String,
    val discountAmount: Double,
    val minimumPurchase: Double? = null,
    val startDate: Date,
    val endDate: Date,
    val usageLimit: Int? = null,
    val isActive: Boolean? = null,
    val createdAt: Date? = null,
//    val promotionUsages: List<PromotionUsage> = emptyList()
)
