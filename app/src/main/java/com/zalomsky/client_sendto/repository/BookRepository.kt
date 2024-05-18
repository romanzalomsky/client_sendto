package com.zalomsky.client_sendto.repository

import com.zalomsky.client_sendto.domain.models.Book
import com.zalomsky.client_sendto.service.BookApiService
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookApiService: BookApiService
) {

    suspend fun getBookApiInterface() = bookApiService.getBooks()

    suspend fun getBookById(bookId: String) = bookApiService.getBookById(bookId)

    suspend fun updateBook(bookId: String, book: Book) = bookApiService.updateBook(bookId, book)

    suspend fun deleteBook(bookId: String) = bookApiService.deleteBook(bookId)

    suspend fun addBook(book: Book) = bookApiService.createBook(book)
}