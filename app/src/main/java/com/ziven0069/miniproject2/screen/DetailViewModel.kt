package com.ziven0069.miniproject2.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ziven0069.miniproject2.database.BukuDao
import com.ziven0069.miniproject2.model.Buku
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val dao: BukuDao) : ViewModel() {
    fun insert(judul: String, isbn: String, kategori: String) {
        val buku = Buku(
            judul = judul,
            isbn = isbn,
            kategori = kategori,
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(buku)
        }
    }

    fun update(id: Long, judul: String, isbn: String, kategori : String) {
        val buku = Buku(
            id = id,
            judul = judul,
            isbn = isbn,
            kategori = kategori
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(buku)
        }
    }

    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO){
            dao.deleteById(id)
        }
    }

    suspend fun getBuku(id: Long): Buku? {
        return dao.getBukuById(id)
    }
}
