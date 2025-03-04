package com.spavv.m.comon.constants

object Routes {
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val HOME = "home"
    const val PRODUCT = "product"
    const val FAVORITE = "favorite"
    const val PROFILE = "profile"
    const val CART = "cart"


    const val SKIN_TYPE= "skin_type"
    //* Nest route from HOME
    const val SKIN_TEST = "skin_test"
    const val SKIN_TEST_RESULT = "skin_test_result"

    // Nest route from Product
    const val PRODUCT_DETAIL = "product_detail"
    const val PRODUCT_DETAIL_HOST = "product_detail/{productId}"
}
