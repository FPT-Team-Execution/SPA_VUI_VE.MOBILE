package com.spavv.m.ui.screens.promotion

import Promotion
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.LocalNavigation
import com.spavv.m.comon.constants.Routes
import com.spavv.m.data.dataSources.PromotionDataSource
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.Paginate
import com.spavv.m.exceptions.UnauthorizedException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PromotionVM(
    private val promotionDataSource: PromotionDataSource
)
    : ViewModel() {

//    init {
//        fetchPromotions();
//    }

    private val _promotions = mutableStateOf<List<Promotion>>(emptyList())
    val promotions: State<List<Promotion>> = _promotions
    private fun updatePromotions(value: List<Promotion>) {
        _promotions.value = value;
    }

    fun fetchPromotions(handingError: (String) -> Unit = {}) {
        //isLoading.value = true;
        viewModelScope.launch {
            try {
                val promotions = promotionDataSource.getPromotions()
                if(promotions != null)
                    updatePromotions(promotions);
            } catch (e: Exception) {
                handingError(e.message.toString())
            }
        }
        //isLoading.value = false;
    }
}