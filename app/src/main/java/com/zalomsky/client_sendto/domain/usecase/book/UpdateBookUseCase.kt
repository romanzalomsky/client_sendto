package com.zalomsky.client_sendto.domain.usecase.book

import com.zalomsky.client_sendto.domain.models.Book
import com.zalomsky.client_sendto.domain.models.Task
import com.zalomsky.client_sendto.repository.BookRepository
import com.zalomsky.client_sendto.repository.TaskRepository
import javax.inject.Inject

class UpdateBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke(bookId: String, book: Book) = bookRepository.updateBook(bookId, book)
}