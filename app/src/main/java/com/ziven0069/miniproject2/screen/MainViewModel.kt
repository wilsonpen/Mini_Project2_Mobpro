package com.ziven0069.miniproject2.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ziven0069.miniproject2.database.BukuDao
import com.ziven0069.miniproject2.model.Buku
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: BukuDao) : ViewModel() {
    val data: StateFlow<List<Buku>> = dao.getBuku().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}