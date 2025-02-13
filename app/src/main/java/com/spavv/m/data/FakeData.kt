package com.spavv.m.data

import com.spavv.m.data.models.Brand
import com.spavv.m.data.models.Category
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.SkinTestOption
import com.spavv.m.data.models.SkinTestQuestion
import com.spavv.m.data.models.SkinType
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
            imageUrl = "https://product.hstatic.net/200000124701/product/ong_nang_la_roche_posay_kiem_soat_dau_spf50_6561_634c_large_95e9a1bea6_8f70821b759a4c27bbaa2561f5e8a0ee_master.jpg",
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
            imageUrl = "https://boribeauty.com/wp-content/uploads/2023/08/avarta-innisfree-Intensive-Triple-shield-Sunscreen-SPF50-PA-50-mL-1.jpg",
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
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFGwOQQx4zv0tMdJKXF_xx0jj8pxBt4zvx0w&s",
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
            imageUrl = "https://m.media-amazon.com/images/I/71njvtsnncL._SL1500_.jpg",
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
            imageUrl = "https://bizweb.dktcdn.net/100/479/330/products/the-ordinary-hyaluronic-acid-2-b5b3.jpg?v=1722936562473",
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
    var mockSkinTestQuestions = listOf(
        SkinTestQuestion(
            questionId = "1",
            question = "What is your skin type?",
            questionOrder = 1,
            isActive = true,
            createdAt = Date(),
            skinTestOptions = listOf(
                SkinTestOption(optionId = "1", optionText = "Oily", skinTypeId = "A"),
                SkinTestOption(optionId = "2", optionText = "Dry", skinTypeId = "B"),
                SkinTestOption(optionId = "3", optionText = "Combination", skinTypeId = "C"),
                SkinTestOption(optionId = "4", optionText = "Sensitive", skinTypeId = "D"),
            )
        ),
        SkinTestQuestion(
            questionId = "2",
            question = "How often do you use skincare products?",
            questionOrder = 2,
            isActive = true,
            createdAt = Date(),
            skinTestOptions = listOf(
                SkinTestOption(optionId = "5", optionText = "Daily", skinTypeId = "A"),
                SkinTestOption(optionId = "6", optionText = "Occasionally", skinTypeId = "B"),
                SkinTestOption(optionId = "7", optionText = "Rarely", skinTypeId = "C"),
            )
        ),
        SkinTestQuestion(
            questionId = "3",
            question = "What is your main skin concern?",
            questionOrder = 3,
            isActive = true,
            createdAt = Date(),
            skinTestOptions = listOf(
                SkinTestOption(optionId = "8", optionText = "Acne", skinTypeId = "A"),
                SkinTestOption(optionId = "9", optionText = "Wrinkles", skinTypeId = "B"),
                SkinTestOption(optionId = "10", optionText = "Dark spots", skinTypeId = "C"),
                SkinTestOption(optionId = "11", optionText = "Redness", skinTypeId = "D"),
            )
        )
    )
    val fakeSkinType = SkinType(
        skinTypeId = "1",
        name = "Oily Skin",
        description = "Skin that produces excess sebum, leading to a shiny complexion and potential acne.Skin that produces excess sebum, leading to a shiny complexion and potential acne.",
        characteristics = "Shiny appearance, enlarged pores, prone to acne and blackheads.",
        recommendedIngredients = "Salicylic Acid, Niacinamide, Hyaluronic Acid.",
        avoidIngredients = "Heavy oils, Alcohol-based products.",
        careInstructions = "Use oil-free moisturizers, cleanse twice daily, exfoliate regularly.",
        isActive = true,
        createdAt = Date(),
        updatedAt = Date(),
    )
}