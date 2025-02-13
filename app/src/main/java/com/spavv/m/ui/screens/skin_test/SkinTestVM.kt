package com.spavv.m.ui.screens.skin_test

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spavv.m.data.FakeData
import com.spavv.m.data.api.SubmitSkinTestRequest
import com.spavv.m.data.dataSources.GetProductsQuery
import com.spavv.m.data.dataSources.SkinTestDataSource
import com.spavv.m.data.models.SkinTestOption
import com.spavv.m.data.models.SkinTestQuestion
import com.spavv.m.data.models.SkinType
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SkinTestVM(private val skinTestDataSource: SkinTestDataSource) : ViewModel() {

    private var _skinTypeResult = mutableStateOf<SkinType?>(null)
    val skinTypeResult: State<SkinType?> = _skinTypeResult
    fun updateSkinTypeResult(value: SkinType?) {
        _skinTypeResult.value = value
    }

    private var _skinTestOptions = mutableStateOf<Map<String, SkinTestOption>>(emptyMap())
    val skinTestOptions: State<Map<String, SkinTestOption>> = _skinTestOptions

    fun updateOrAddOption(questionId: String, option: SkinTestOption) {
        _skinTestOptions.value = _skinTestOptions.value.toMutableMap().apply {
            put(questionId, option)
        }
    }


    private var _skinTestQuestions = mutableStateOf<List<SkinTestQuestion>>(emptyList())
    val skinTestQuestions: State<List<SkinTestQuestion>> = _skinTestQuestions
    fun updateQuestions(value: List<SkinTestQuestion>) {
        _skinTestQuestions.value = value
    }

    //* Always run after variables 's definition
    init {
        fetchQuestions()
    }

    private fun fetchQuestions() {
        viewModelScope.launch {
            try {
                val questions = skinTestDataSource.getSkinTests()
                //* Fake data
                //val questions: List<SkinTestQuestion> = FakeData.mockSkinTestQuestions
                updateQuestions(questions);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessages = _toastMessage.asSharedFlow()

    fun fetchToastMessages(message: String) {
        viewModelScope.launch {
            _toastMessage.emit(message)
            delay(1000) // Đợi 1 giây
        }
    }

    suspend fun submitSkinTest(): Boolean {
        val isAllChosen = _skinTestOptions.value.size == _skinTestQuestions.value.size
        if (!isAllChosen) {
            fetchToastMessages("Vui lòng chọn tất cả câu hỏi!")
            return false;
        }

        return try {
            val request = SubmitSkinTestRequest(chosenOptions = _skinTestOptions.value)
            val skinTypeResult = viewModelScope.async {
                skinTestDataSource.submitSkinTest(request)
            }.await()

            if (skinTypeResult != null) {
                //fetchToastMessages("Gửi bài kiểm tra thành công!")
                updateSkinTypeResult(skinTypeResult)
                true
            } else {
                fetchToastMessages("Gửi bài kiểm tra thất bại!")
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            fetchToastMessages("Đã xảy ra lỗi, vui lòng thử lại sau!")
            false
        }
    }

}