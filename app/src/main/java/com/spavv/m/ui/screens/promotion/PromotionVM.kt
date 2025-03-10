package com.spavv.m.ui.screens.promotion

import Promotion
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.dataSources.PromotionDataSource
import com.spavv.m.data.models.Product
import com.spavv.m.data.models.base.Paginate
import kotlinx.coroutines.launch

class PromotionVM(
    private val promotionDataSource: PromotionDataSource
)
    : ViewModel() {

    init {
        fetchPromotions();
    }

    private val _promotions = mutableStateOf<List<Promotion>>(emptyList())
    val promotions: State<List<Promotion>> = _promotions
    private fun updatePromotions(value: List<Promotion>) {
        _promotions.value = value;
    }

    fun fetchPromotions() {
        //isLoading.value = true;
        viewModelScope.launch {
            try {
                val promotions = promotionDataSource.getPromotions()
                if(promotions != null)
                    updatePromotions(promotions);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        //isLoading.value = false;
    }
}