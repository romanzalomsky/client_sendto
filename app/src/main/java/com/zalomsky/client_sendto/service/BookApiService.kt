package com.zalomsky.client_sendto.service

import com.zalomsky.client_sendto.domain.models.Book
import com.zalomsky.client_sendto.domain.models.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BookApiService {

    @GET("/auth/books")
    suspend fun getBooks(): List<Book>

    @GET("/auth/books/{bookId}")
    suspend fun getBookById(@Path("bookId") bookId: String): Book?

    @PUT("/auth/books/{bookId}")
    suspend fun updateBook(
        @Path("bookId") bookId: String,
        @Body book: Book
    ): Response<Book>

    @POST("/auth/books/add")
    suspend fun createBook(@Body book: Book): Response<Book>

    @DELETE("/auth/books/{bookId}")
    suspend fun deleteBook(@Path("bookId") bookId: String)
}