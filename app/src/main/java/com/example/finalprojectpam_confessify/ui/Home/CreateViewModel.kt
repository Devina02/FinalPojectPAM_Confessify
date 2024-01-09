package com.example.finalprojectpam_confessify.ui.Home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectpam_confessify.data.ConfessData
import com.example.finalprojectpam_confessify.repository.ConfessRepository
import kotlinx.coroutines.launch

class InsertViewModel(private val confessRepository: ConfessRepository) : ViewModel() {
    var insertConfessState by mutableStateOf(InsertUiState())
        private set
    fun updateInsertKontakState(insertUiEvent: InsertUiEvent) {
        insertConfessState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertConfess() {
        viewModelScope.launch {
            try {
                confessRepository.insertConfess(insertConfessState.insertUiEvent.toConfessData())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent(),
)

data class InsertUiEvent(
    val fess: String = "",
)

fun InsertUiEvent.toConfessData(): ConfessData = ConfessData(
    fess = fess,
)

fun ConfessData.toUiStateConfess(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent(),
)

fun ConfessData.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    fess = fess
)