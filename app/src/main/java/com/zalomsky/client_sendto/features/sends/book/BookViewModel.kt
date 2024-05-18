package com.zalomsky.client_sendto.features.sends.book

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalomsky.client_sendto.domain.models.Book
import com.zalomsky.client_sendto.domain.usecase.book.AddBookUseCase
import com.zalomsky.client_sendto.domain.usecase.book.DeleteBookUseCase
import com.zalomsky.client_sendto.domain.usecase.book.GetBookByIdUseCase
import com.zalomsky.client_sendto.domain.usecase.book.GetBookUseCase
import com.zalomsky.client_sendto.domain.usecase.book.UpdateBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBookUseCase: GetBookUseCase,
    private val addBookUseCase: AddBookUseCase,
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val updateBookUseCase: UpdateBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
): ViewModel() {

    private val _book = MutableStateFlow<Book?>(null)
    val book: StateFlow<Book?>
        get() = _book

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>>
        get() = _books

    fun getBooksList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val books = getBookUseCase()
                _books.postValue(books)
            } catch (e: Exception) {
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }

    fun getBookById(bookId: String) {
        viewModelScope.launch {
            try {
                val book = getBookByIdUseCase(bookId)
                _book.value = book
            } catch (e: Exception) {
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }

    fun addBook(book: Book, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                addBookUseCase(book)
                onSuccess()
            } catch (e: Exception) {
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }

    fun updateBook(bookId: String, book: Book, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                updateBookUseCase(bookId, book)
                onSuccess()
            } catch (e: Exception) {
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }

    fun deleteBook(bookId: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                deleteBookUseCase(bookId)
                onSuccess()
            } catch (e: Exception) {
                Log.e("asdfghjk", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }
}