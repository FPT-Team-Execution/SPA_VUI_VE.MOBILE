package com.spavv.m.ui.screens.skin_type

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.dataSources.SkinTypeDataSource
import com.spavv.m.data.models.SkinType
import kotlinx.coroutines.launch

class SkinTypeVM(private val skinTypeDataSource: SkinTypeDataSource) : ViewModel() {

    private var _skinTypes = mutableStateOf<List<SkinType>>(emptyList())
    val skinTypes: State<List<SkinType>> = _skinTypes
    fun updateSkinTypes(value: List<SkinType>) {
        _skinTypes.value = value
    }

    //* Always run after variables 's definition
    init {
        fetchSkinTypes()
    }

    private fun fetchSkinTypes() {
        viewModelScope.launch {
            try {
                val skinTypes = skinTypeDataSource.getSkinTypes()
                updateSkinTypes(skinTypes);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}