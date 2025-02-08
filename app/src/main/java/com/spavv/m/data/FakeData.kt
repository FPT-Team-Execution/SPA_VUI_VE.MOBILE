package com.spavv.m.data

import com.spavv.m.data.models.Brand
import com.spavv.m.data.models.Category
import com.spavv.m.data.models.Product
import java.util.Date

object FakeData {
    val mockProducts = listOf(
        Product(
            productId = "p1",
            name = "Serum Vitamin C",
            description = "Dưỡng trắng và làm sáng da.",
            price = 399000.0,
            categoryId = "c1",
            brandId = "brand1",
            stockQuantity = 100,
            imageUrl = "https://placehold.co/191x100/png",
            ingredients = "Vitamin C, Hyaluronic Acid",
            isActive = true,
            createdAt = Date(),
            updatedAt = Date(),
            brand = null,
            category = null
        ),
        Product(
            productId = "p2",
            name = "Kem Chống Nắng SPF 50",
            description = "Bảo vệ da khỏi tia UV.",
            price = 299000.0,
            categoryId = "c2",
            brandId = "brand2",
            stockQuantity = 200,
            imageUrl = "https://placehold.co/150x150/png",
            ingredients = "Titanium Dioxide, Zinc Oxide",
            isActive = true,
            createdAt = Date(),
            updatedAt = Date(),
            brand = null,
            category = null
        ),
        Product(
            productId = "p3",
            name = "Sữa Rửa Mặt Trà Xanh",
            description = "Làm sạch sâu và giảm mụn.",
            price = 199000.0,
            categoryId = "c1",
            brandId = "brand3",
            stockQuantity = 150,
            imageUrl = "https://placehold.co/150x150/png",
            ingredients = "Trà xanh, Glycerin",
            isActive = true,
            createdAt = Date(),
            updatedAt = Date(),
            brand = null,
            category = null
        ),
        Product(
            productId = "p4",
            name = "Mặt Nạ Dưỡng Ẩm",
            description = "Cấp ẩm và phục hồi làn da.",
            price = 250000.0,
            categoryId = "c3",
            brandId = "brand4",
            stockQuantity = 120,
            imageUrl = "https://placehold.co/150x150/png",
            ingredients = "Aloe Vera, Hyaluronic Acid",
            isActive = true,
            createdAt = Date(),
            updatedAt = Date(),
            brand = null,
            category = null
        ),
        Product(
            productId = "p5",
            name = "Kem Dưỡng Da Ban Đêm",
            description = "Phục hồi da khi ngủ.",
            price = 499000.0,
            categoryId = "c3",
            brandId = "brand5",
            stockQuantity = 90,
            imageUrl = "https://placehold.co/150x150/png",
            ingredients = "Niacinamide, Peptides",
            isActive = true,
            createdAt = Date(),
            updatedAt = Date(),
            brand = null,
            category = null
        )
    )
    val mockBrands = listOf(
        Brand(
            brandId = "brand1",
            name = "L'Oreal",
            description = "Thương hiệu mỹ phẩm hàng đầu thế giới.",
            imageUrl = "https://placehold.co/150x150/png",
            createdAt = Date(),
            updatedAt = Date(),
            status = "Active",
            products = mockProducts.filter { it.brandId == "brand1" }
        ),
        Brand(
            brandId = "brand2",
            name = "Neutrogena",
            description = "Chuyên gia chăm sóc da từ Mỹ.",
            imageUrl = "https://placehold.co/150x150/png",
            createdAt = Date(),
            updatedAt = Date(),
            status = "Active",
            products = mockProducts.filter { it.brandId == "brand2" }
        ),
        Brand(
            brandId = "brand3",
            name = "The Ordinary",
            description = "Thương hiệu nổi tiếng với các sản phẩm chăm sóc da tinh khiết.",
            imageUrl = "https://placehold.co/150x150/png",
            createdAt = Date(),
            updatedAt = Date(),
            status = "Active",
            products = mockProducts.filter { it.brandId == "brand3" }
        ),
        Brand(
            brandId = "brand4",
            name = "Innisfree",
            description = "Thương hiệu Hàn Quốc chuyên về mỹ phẩm thiên nhiên.",
            imageUrl = "https://placehold.co/150x150/png",
            createdAt = Date(),
            updatedAt = Date(),
            status = "Active",
            products = mockProducts.filter { it.brandId == "brand4" }
        ),
        Brand(
            brandId = "brand5",
            name = "La Roche-Posay",
            description = "Thương hiệu dược mỹ phẩm chuyên biệt cho làn da nhạy cảm.",
            imageUrl = "https://placehold.co/150x150/png",
            createdAt = Date(),
            updatedAt = Date(),
            status = "Active",
            products = mockProducts.filter { it.brandId == "brand5" }
        )
    )
    val mockCategories = listOf(
        Category(
            categoryId = "c1",
            name = "Chăm sóc da mặt",
            description = "Các sản phẩm giúp nuôi dưỡng và làm đẹp làn da.",
            imageUrl = "https://placehold.co/150x150/png",
            isActive = true,
            parentCategoryId = null,
            displayOrder = 1,
            createdAt = Date(),
            updatedAt = Date(),
            parentCategory = null,
            inverseParentCategory = emptyList(),
            products = mockProducts.filter { it.categoryId == "c1" }
        ),
        Category(
            categoryId = "c2",
            name = "Chống nắng",
            description = "Bảo vệ da khỏi tác động của tia UV.",
            imageUrl = "https://placehold.co/150x150/png",
            isActive = true,
            parentCategoryId = null,
            displayOrder = 2,
            createdAt = Date(),
            updatedAt = Date(),
            parentCategory = null,
            inverseParentCategory = emptyList(),
            products = mockProducts.filter { it.categoryId == "c2" }
        ),
        Category(
            categoryId = "c3",
            name = "Dưỡng ẩm",
            description = "Cung cấp độ ẩm và giữ da mềm mại.",
            imageUrl = "https://placehold.co/150x150/png",
            isActive = true,
            parentCategoryId = null,
            displayOrder = 3,
            createdAt = Date(),
            updatedAt = Date(),
            parentCategory = null,
            inverseParentCategory = emptyList(),
            products = mockProducts.filter { it.categoryId == "c3" }
        )
    )

}