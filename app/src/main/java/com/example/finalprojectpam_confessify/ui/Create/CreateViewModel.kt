package com.example.finalprojectpam_confessify.ui.Create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.finalprojectpam_confessify.data.ConfessData
import com.example.finalprojectpam_confessify.repository.RepositoryConfess

class InsertViewModel(private val repositoryConfess: RepositoryConfess) : ViewModel() {
    var insertConfessState by mutableStateOf(InsertUiState())
        private set

    fun validasiInput(uiState: InsertUiEvent = insertConfessState.insertUiEvent): Boolean {
        return with(uiState) {
            fess.isNotBlank()
        }
    }
    fun updateInsertState(insertUiEvent: InsertUiEvent) {
        insertConfessState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun saveConfess() {
        if (validasiInput()) {
            repositoryConfess.insertConfess(insertConfessState.insertUiEvent.toConfessData())
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent(),
    val isEntryValid: Boolean = false
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