package com.spavv.m.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

object ScaffoldLayoutVM : ViewModel() {
    private var _selectedIndex = mutableStateOf<Int>(0);
    val navIndex: State<Int> get() = _selectedIndex;
    fun updateNavIndex(value: Int){
        _selectedIndex.value = value;

    }
}